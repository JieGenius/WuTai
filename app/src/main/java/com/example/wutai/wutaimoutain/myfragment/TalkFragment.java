package com.example.wutai.wutaimoutain.myfragment;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wutai.wutaimoutain.MainActivity;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Services.services;
import com.example.wutai.wutaimoutain.Talk.AllTalk;
import com.example.wutai.wutaimoutain.Talk.TalkAdapter;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class TalkFragment extends Fragment {//指说说界面
    public List<AllTalk.TalkArrBean> list;
    private static final String TAG = "TalkFragment";
    private RecyclerView talkRecyView;
    private SwipeRefreshLayout swipeRefreshLayout;
    public Handler handler;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shequfragment, container, false);
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        initreviews();
        swipeRefreshLayout = view.findViewById(R.id.talk_swipe_refersh_layout);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getTalkData();
            }
        });
        talkRecyView = (RecyclerView)view.findViewById(R.id.my_frgment_Talk_recy_view);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        if(MainActivity.allTalkData!=null){
            list = MainActivity.allTalkData.getTalkArr();
        }
        else{
            list = new ArrayList<>();
        }


        final TalkAdapter talkAdapter = new TalkAdapter(list,getActivity());
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
            }
        };
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
        //client.newBuilder().readTimeout(5000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
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

}
