package com.example.wutai.wutaimoutain.myfragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wutai.wutaimoutain.MainActivity;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.SimiaoSet.AdtAdapter;
import com.example.wutai.wutaimoutain.SimiaoSet.Advertisment;
import com.example.wutai.wutaimoutain.SimiaoSet.AutoPollRecyclerView;
import com.example.wutai.wutaimoutain.SimiaoSet.Simiao;
import com.example.wutai.wutaimoutain.SimiaoSet.SimiaoAdapter;
import com.example.wutai.wutaimoutain.Stroy.Single_stroy_activity;
import com.example.wutai.wutaimoutain.Utils.GridSpacingItemDecoration;
import com.example.wutai.wutaimoutain.VR.VR_showactivity;
import com.example.wutai.wutaimoutain.Wutaijianjie_activity;
import com.example.wutai.wutaimoutain.allsimiaos.Allsimiaoyilan_test;
import com.example.wutai.wutaimoutain.mine.UserInfo;
import com.example.wutai.wutaimoutain.navigation.NavigationHomeActivity;
import com.example.wutai.wutaimoutain.yinglian.YingLianHome;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {
    private int i = 1;
    private static Timer timer;
    private ArrayList<Simiao> guobaos = new ArrayList<>();
    private ArrayList<Simiao> shenbaos = new ArrayList<>();
    private ArrayList<Advertisment> wutai_view = new ArrayList<>();
    private ArrayList<Advertisment> adverts = new ArrayList<>();
    private RecyclerView guobao, shenbao, wutainews;
    private AutoPollRecyclerView view_wutai, advertisments;
    private FloatingActionButton icon_jianjie, icon_allsimiaos, icon_couplets, icon_daohang,
            icon_stroy, icon_vr, icon_manyouWuTai, icon_bussness;
    private SharedPreferences sharedPreferences;
    public static HomeFragment newInstance(String param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public HomeFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, container, false);
        sharedPreferences = getActivity().getSharedPreferences("user_message",Context.MODE_PRIVATE);
        Bundle bundle = getArguments();
        initlist();
        initview(view);
        String agrs1 = bundle.getString("agrs1");
        SimiaoAdapter adapter = new SimiaoAdapter(getActivity(), guobaos);
        SimiaoAdapter adapter1 = new SimiaoAdapter(getActivity(), shenbaos);
        AdtAdapter adtAdapter = new AdtAdapter(getActivity(), wutai_view);
        AdtAdapter adtAdapter1 = new AdtAdapter(getActivity(), adverts);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager layoutManager1 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager layoutManager3 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        guobao.setLayoutManager(layoutManager);
        guobao.setAdapter(adapter);
        shenbao.setLayoutManager(layoutManager1);
        shenbao.setAdapter(adapter1);
        view_wutai.setLayoutManager(layoutManager2);
        view_wutai.setAdapter(adtAdapter);
        advertisments.setLayoutManager(layoutManager3);
        advertisments.setAdapter(adtAdapter1);
        int spanCount = 20; // 3 columns
        int spacing = 20; // 50px
        boolean includeEdge = false;
        guobao.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        if (timer == null) {
            timer = new Timer();
            timer.schedule(timerTask, 2000, 13000);
        };
        icon_allsimiaos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Allsimiaoyilan_test.class);
                startActivity(intent);
            }
        });
        icon_couplets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), YingLianHome.class));

            }
        });
        icon_jianjie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(getActivity(), Wutaijianjie_activity.class);
                startActivity(intent1);
            }
        });
        icon_vr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), VR_showactivity.class);
                startActivity(intent);
            }
        });
        icon_stroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Single_stroy_activity.actionstart(getActivity(),"五台山");
            }
        });
        icon_daohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NavigationHomeActivity.class));
            }
        });
        icon_manyouWuTai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),com.example.wutai.wutaimoutain.ManYouWuTai.MainActivity.class);
                startActivity(intent);
            }
        });
        icon_bussness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),com.example.wutai.wutaimoutain.bussness.MainActivity.class);
                startActivity(intent);
            }
        });
        return view;

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                view_wutai.start();
                advertisments.start();
            } else {
                view_wutai.stop();
                advertisments.stop();
            }
            super.handleMessage(msg);
        }
    };
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            if (i % 2 == 0) message.what = 1;
            else message.what = 0;
            i++;
            handler.sendMessage(message);
        }
    };

    public void initview(View view) {
        guobao = (RecyclerView) view.findViewById(R.id.guobao);
        shenbao = (RecyclerView) view.findViewById(R.id.shenbao);
        view_wutai = (AutoPollRecyclerView) view.findViewById(R.id.view_wuati);
//        wutainews = (RecyclerView) view.findViewById(R.id.news_show);
        advertisments = (AutoPollRecyclerView) view.findViewById(R.id.advertisments_wutai);
        icon_jianjie = (FloatingActionButton) view.findViewById(R.id.simple_text);
        icon_allsimiaos = (FloatingActionButton) view.findViewById(R.id.all_simiao);
        icon_couplets = (FloatingActionButton) view.findViewById(R.id.couplets);
        icon_daohang = (FloatingActionButton) view.findViewById(R.id.navigation);
        icon_stroy = (FloatingActionButton) view.findViewById(R.id.stroy);
        icon_vr = (FloatingActionButton) view.findViewById(R.id.vr);
        icon_manyouWuTai = (FloatingActionButton) view.findViewById(R.id.yunyinjieshuo);
        icon_bussness = (FloatingActionButton) view.findViewById(R.id.cook_live);
//        shareSomething = (FloatingActionButton)view.findViewById(R.id.share_with_other);
    }

    public void initlist() {
        guobaos.clear();
        shenbaos.clear();
        wutai_view.clear();
        adverts.clear();
        String username = "勋鹿苍苍";
        Calendar c = Calendar.getInstance();//可以对每个时间域单独修改

        int year = c.get(Calendar.YEAR);

        int month = c.get(Calendar.MONTH);

        int date = c.get(Calendar.DATE);

        int hour = c.get(Calendar.HOUR_OF_DAY);

        int minute = c.get(Calendar.MINUTE);

        int second = c.get(Calendar.SECOND);
        SimpleDateFormat df = new SimpleDateFormat("mm:ss");//设置日期格式
        final String time = hour + ":" + minute;
        String content = "五台山佛教圣地值得一游！";

        guobaos.add(new Simiao(R.drawable.xiantongsisi, "显通寺"));
        guobaos.add(new Simiao(R.drawable.temple_lh_iv_icon, "罗睺寺"));
        guobaos.add(new Simiao(R.drawable.pusaiding, "菩萨顶"));
        guobaos.add(new Simiao(R.drawable.temple_tys_legend_icon1, "塔院寺"));
        guobaos.add(new Simiao(R.drawable.temple_bs_iv_icon, "碧山寺"));


        shenbaos.add(new Simiao(R.drawable.dayuanzhao, "大圆照寺"));
        shenbaos.add(new Simiao(R.drawable.jiege, "金阁寺"));
        shenbaos.add(new Simiao(R.drawable.longquan, "龙泉寺"));
        shenbaos.add(new Simiao(R.drawable.nanshan, "南山寺"));
        shenbaos.add(new Simiao(R.drawable.shuxiang, "殊像寺"));
        adverts.add(new Advertisment(R.drawable.witaishanadvet1));

        adverts.add(new Advertisment(R.drawable.wutaishanadvetisment2));
        adverts.add(new Advertisment(R.drawable.wutaishanadveryis3));

        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung0));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung2));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung3));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung4));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung5));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung7));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung8));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung9));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung10));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung12));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung13));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung14));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung15));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung17));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung18));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung19));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung20));
        wutai_view.add(new Advertisment(R.drawable.wutaifenggaung21));
//            shares.add(new Share(R.drawable.wutai, R.drawable.wutaishan, username, time, content));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view_wutai.stop();
        advertisments.stop();
    }
}
