package com.example.wutai.wutaimoutain.myfragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.wutai.wutaimoutain.MainActivity;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Services.services;
import com.example.wutai.wutaimoutain.Talk.AllTalk;
import com.example.wutai.wutaimoutain.Talk.TalkAdapter;
import com.example.wutai.wutaimoutain.sendTalk.Bimp;
import com.example.wutai.wutaimoutain.sendTalk.FileUtils;
import com.example.wutai.wutaimoutain.sendTalk.SendTalkActivity;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class TalkFragment extends Fragment {//指说说界面
    public List<AllTalk.TalkArrBean> list;
    private static final String TAG = "TalkFragment";
    private RecyclerView talkRecyView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private  SharedPreferences sharedPreferences;
    public LinearLayout sendCommentLayout;
    public Handler handler;
    private View.OnKeyListener backListener;
    private  TalkAdapter talkAdapter;
    public EditText commentContent;
    private Button commentSend;

    public static TalkFragment newInstance(String param1) {
        TalkFragment fragment = new TalkFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public TalkFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shequfragment, container, false);
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        initreviews();
        sharedPreferences = getActivity().getSharedPreferences("user_message", Context.MODE_PRIVATE);
        swipeRefreshLayout = view.findViewById(R.id.talk_swipe_refersh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTalkData();
            }
        });
        talkRecyView = (RecyclerView)view.findViewById(R.id.my_frgment_Talk_recy_view);
        sendCommentLayout = view.findViewById(R.id.talk_send_comment_layout);
        commentContent = view.findViewById(R.id.talk_et_comment_content);
        commentSend = view.findViewById(R.id.talk_bt_comment_send);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        if(MainActivity.allTalkData!=null){
            list = MainActivity.allTalkData.getTalkArr();
        }
        else{
            list = new ArrayList<>();
        }
        talkAdapter= new TalkAdapter(list,getActivity(),sendCommentLayout);
        talkRecyView.setLayoutManager(layoutManager1);
        talkRecyView.setAdapter(talkAdapter);
        handler = new Handler(Looper.getMainLooper()){
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 5){
                    if(list.size() == 0){
                        list.addAll(MainActivity.allTalkData.getTalkArr());
                        talkAdapter.notifyDataSetChanged();
                    }
                }
                else if(msg.what == 6){
                    talkAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
                }
                else if(msg.what == 7){
                    Toast.makeText(getActivity(),"评论发表成功",Toast.LENGTH_SHORT).show();
                    list.get(msg.arg1).getCommArr().add((AllTalk.TalkArrBean.CommArrBean)msg.obj);
                    talkAdapter.notifyDataSetChanged();
                }
                else if(msg.what ==8){
                    Toast.makeText(getActivity(),(String)msg.obj,Toast.LENGTH_SHORT).show();
                }
            }
        };
        commentContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length() == 0){
                    commentSend.setEnabled(false);
                }
                else{
                    commentSend.setEnabled(true);
                }
            }
        });
        commentSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(sharedPreferences.getBoolean("isLogin",false)==false){
                    Toast.makeText(getActivity(),"请先登录",Toast.LENGTH_SHORT).show();
                }
                else{
                    AllTalk.TalkArrBean.CommArrBean commArrBean = new AllTalk.TalkArrBean.CommArrBean();
                    commArrBean.setName(sharedPreferences.getString("name","游客"));
                    commArrBean.setContent(commentContent.getText().toString());
                    sendCommentToService(commentContent.getText().toString(),commArrBean,talkAdapter.getClickedPosition());

                    commentContent.setText("");
                    sendCommentLayout.setVisibility(View.GONE);

                    InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
                }
            }
        });
        return view;
    }
    public void initreviews(){
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");//设置日期格式
    }
    public void getTalkData() {
        String url = services.urlGetTalk;
        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        requestBody.addFormDataPart("page", "0");
        final Request request = new Request.Builder().url(url).post(requestBody.build()).tag(getActivity()).build();
        client.newBuilder().readTimeout(10,TimeUnit.MINUTES).connectTimeout(10,TimeUnit.MINUTES).writeTimeout(10,TimeUnit.MINUTES).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: "+e.getMessage() );
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                AllTalk allTalkData =new Gson().fromJson(response.body().string(),AllTalk.class);
                if(TalkFragment.this!=null){
                    list.clear();
                    list.addAll(allTalkData.getTalkArr());
                    handler.sendEmptyMessage(6);
                }
            }
        });
    }
    private void sendCommentToService(String content, final AllTalk.TalkArrBean.CommArrBean commArrBean,final int pos){


        String url = services.urlAddComm;

        OkHttpClient client = new OkHttpClient();
        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);

        requestBody.addFormDataPart("user_phone", sharedPreferences.getString("phone","15135810918"));
        requestBody.addFormDataPart("talk_id", list.get(talkAdapter.getClickedPosition()).getTalk_id());
        requestBody.addFormDataPart("comment_content", content);
        Request request = new Request.Builder().url(url).post(requestBody.build()).tag(getActivity()).build();
        client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String errorMsg = e.getMessage();
                Log.e("失败", errorMsg);
                Message msg = new Message();
                msg.what = 8;
                msg.obj = errorMsg;
                handler.sendMessage(msg);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e("成功", response.body().string());
                Message message = new Message();
                message.arg1 = pos;
                message.obj = commArrBean;
                message.what=7;
                handler.sendMessage(message);
            }
        });
    }


}
