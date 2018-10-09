package com.example.wutai.wutaimoutain.myfragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wutai.wutaimoutain.MainActivity;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Services.services;
import com.example.wutai.wutaimoutain.mine.FeedbackActivity;
import com.example.wutai.wutaimoutain.mine.LoginActivity;
import com.example.wutai.wutaimoutain.mine.SettingActivity;
import com.example.wutai.wutaimoutain.mine.UserInfo;
import com.example.wutai.wutaimoutain.mine.Youji;
import com.example.wutai.wutaimoutain.mine.userCenterActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MineFragmnet extends Fragment {
    private MainActivity activity;
    private static final String TAG = "MineFragmnet";
    AppBarLayout main_app_bar_layout;
    @BindView(R.id.mine_my_qq)
    ImageButton mineMyQq;
    @BindView(R.id.mine_my_weixin)
    ImageButton mineMyWeixin;
    @BindView(R.id.mine_bt_login)
    Button mineBtLogin;
    @BindView(R.id.mine_user_center)
    TextView mineUserCenter;
    @BindView(R.id.mine_tour_favorite)
    TextView mineTourFavorite;
    @BindView(R.id.mine_order)
    TextView mineOrder;
    @BindView(R.id.mine_youji)
    TextView mineYouji;
    @BindView(R.id.mine_history)
    TextView mineHistory;
    @BindView(R.id.mine_feedback)
    TextView mineFeedback;
    @BindView(R.id.call_me)
    TextView callMe;
    @BindView(R.id.mine_settings)
    TextView mineSettings;
    Unbinder unbinder;
    @BindView(R.id.mine_my_phone)
    ImageButton mineMyPhone;
    @BindView(R.id.mine_not_login_relative_layout)
    RelativeLayout mineNotLoginRelativeLayout;
    @BindView(R.id.mine_login_relative_layout)
    RelativeLayout mineLoginRelativeLayout;
    @BindView(R.id.mine_civ_user_pic)
    CircleImageView mineCivUserPic;
    @BindView(R.id.mine_tv_username)
    TextView mineTvUsername;
    Handler handler;
    ProgressDialog progressDialog;
    SharedPreferences sharedPreferences;

    public static MineFragmnet newInstance(String param1) {
        MineFragmnet fragment = new MineFragmnet();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public MineFragmnet() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        View view = inflater.inflate(R.layout.minefragment, container, false);
        main_app_bar_layout = getActivity().findViewById(R.id.main_app_bar_layout);
        sharedPreferences = getActivity().getSharedPreferences("user_message", Context.MODE_PRIVATE);
        Bundle bundle = getArguments();
        String agrs1 = bundle.getString("agrs1");
        unbinder = ButterKnife.bind(this, view);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what == 0x11 && msg.obj!=null){
                    UserInfo info = (UserInfo) msg.obj;
                    mineTvUsername.setText("用户名："+info.getName());
                    NavigationView navigationView = ((MainActivity)getActivity()).findViewById(R.id.nav_view);
                    TextView userPhoneNumber = navigationView.findViewById(R.id.nav_tv_user_phone);
                    TextView  userName = navigationView.findViewById(R.id.nav_tv_user_name);
                    CircleImageView imageView = navigationView.findViewById(R.id.nav_civ_user_head_pic);
                    userPhoneNumber.setText("TEL:"+sharedPreferences.getString("phone","8888"));
                    userName.setText("用户名："+info.getName());
                    if(UserInfo.stream2Drawable(info.getHead_pic_stream())!=null){
                        mineCivUserPic.setImageDrawable(UserInfo.stream2Drawable(info.getHead_pic_stream()));
                        imageView.setImageDrawable(UserInfo.stream2Drawable(info.getHead_pic_stream()));
                    }
                    else{
                        mineCivUserPic.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.default_head_pic));
                        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.default_head_pic));
                    }

                    progressDialog.dismiss();
                }
                else if(msg.what == 0x11){
                    Toast.makeText(getActivity(),"数据获取失败，请检查网络连接",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        };
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        boolean isLogin = sharedPreferences.getBoolean("isLogin",false);
        boolean isFirstLogin = sharedPreferences.getBoolean("isFirstLogin",true);
        String name = sharedPreferences.getString("name","");
        if(isLogin){
            mineNotLoginRelativeLayout.setVisibility(View.GONE);
            mineLoginRelativeLayout.setVisibility(View.VISIBLE);
            if(isFirstLogin){
                progressDialog =ProgressDialog.show(getActivity(),null,"正在获取用户信息",true,false);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        // Simulate network access.
                        String url = services.urlGetPersonInfo;
                        OkHttpClient client = new OkHttpClient();
                        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
                        requestBody.addFormDataPart("phone",sharedPreferences.getString("phone",""));
                        Request request = new Request.Builder().url(url).post(requestBody.build()).tag(getActivity()).build();
                        client.newBuilder().readTimeout(50000, TimeUnit.MILLISECONDS).build().newCall(request).enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                handler.sendEmptyMessage(0x11);
                            }
                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                String result = response.body().string();
                                UserInfo userInfo = new Gson().fromJson(result,UserInfo.class);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("name",userInfo.getName());
                                editor.putString("sex",userInfo.getSex());
                                editor.putString("job",userInfo.getJob());
                                editor.putString("signature",userInfo.getSignature());
                                editor.putString("instruction",userInfo.getIntroduction());
                                editor.putBoolean("isFirstLogin",false);
                                editor.putString("userPicture",userInfo.getHead_pic_stream());
                                editor.apply();
                                Message message = handler.obtainMessage();
                                message.obj=userInfo;
                                message.what=0x11;
                                handler.sendMessage(message);
                            }
                        });
                    }
                }).start();

            }
            else{
                mineTvUsername.setText("用户名："+name);
                if(UserInfo.stream2Drawable(sharedPreferences.getString("userPicture",""))!=null){
                    mineCivUserPic.setImageDrawable(UserInfo.stream2Drawable(sharedPreferences.getString("userPicture","")));
                }
                else {
                    mineCivUserPic.setImageBitmap(BitmapFactory.decodeResource(getResources(),R.drawable.default_head_pic));
                }
            }
        }
        else{
            mineNotLoginRelativeLayout.setVisibility(View.VISIBLE);
            mineLoginRelativeLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //main_app_bar_layout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //main_app_bar_layout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    @OnClick({R.id.mine_my_phone, R.id.mine_my_qq, R.id.mine_my_weixin, R.id.mine_bt_login,
            R.id.mine_user_center, R.id.mine_tour_favorite, R.id.mine_order, R.id.mine_youji,
            R.id.mine_history, R.id.mine_feedback, R.id.call_me, R.id.mine_settings})
    public void onViewClicked(View view) {
        final Intent intent;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("抱歉");
        builder.setMessage("程序猿可能是个傻子，该部分功能还未实现，给您带来的不便请谅解！");
        builder.setCancelable(true);
        switch (view.getId()) {
            case R.id.mine_my_phone:
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_my_qq:
//                intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);
                builder.show();
                break;
            case R.id.mine_my_weixin:
//                intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);
                builder.show();
                break;
            case R.id.mine_bt_login:
                intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_user_center:
                boolean isLogin = sharedPreferences.getBoolean("isLogin",false);
                if(isLogin){
                    intent = new Intent(getActivity(),userCenterActivity.class);
                    startActivity(intent);
                }
                else{
                    Snackbar.make(view,"您当前未登录，是否跳到登录界面",3000).setAction("是", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent1 = new Intent(getActivity(),LoginActivity.class);
                            startActivity(intent1);
                        }
                    }).show();
                }
                break;
            case R.id.mine_tour_favorite:
//                intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);
                builder.show();
                break;
            case R.id.mine_order:
//                intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);
                builder.show();
                break;
            case R.id.mine_youji:
                intent = new Intent(getActivity(), Youji.class);
                startActivity(intent);
                break;
            case R.id.mine_history:
//                intent = new Intent(getActivity(), LoginActivity.class);
//                startActivity(intent);
                builder.show();
                break;
            case R.id.mine_feedback:
                intent = new Intent(getActivity(), FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.call_me:
                intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"15835107065"));
                startActivity(intent);
                break;
            case R.id.mine_settings:
                intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                break;
        }
    }

}
