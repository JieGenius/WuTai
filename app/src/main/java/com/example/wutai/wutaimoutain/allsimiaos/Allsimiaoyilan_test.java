package com.example.wutai.wutaimoutain.allsimiaos;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.example.wutai.wutaimoutain.R;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Allsimiaoyilan_test extends AppCompatActivity {
    Toolbar toolbar;
    @BindView(R.id.all_simiao_recy_view_guobao)
    RecyclerView allSimiaoRecyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allsimiaoyilan_test);
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.yinglian_toolbar_alls1);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_wuta);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        allSimiaoRecyView.setLayoutManager(staggeredGridLayoutManager);
        List<Integer> list = new ArrayList<>();
        allSimiaoRecyView.setNestedScrollingEnabled(false);
        list.add(R.drawable.temple_xt_iv_icon);
        list.add(R.drawable.pusaiding);
        list.add(R.drawable.temple_tys_legend_icon1);
        list.add(R.drawable.temple_bs_iv_icon);
        list.add(R.drawable.temple_lh_iv_icon);
        list.add(R.drawable.temple_sx_iv_icon);
        list.add(R.drawable.temple_ns_iv_icon);
        list.add(R.drawable.temple_jg_iv_icon);
        list.add(R.drawable.temple_lq_iv_icon);
        list.add(R.drawable.dayuanzhao);
        List<String> list1 = new ArrayList<>();
        list1.add("国保：显通寺");
        list1.add("国保：菩萨顶");
        list1.add("国保：塔院寺");
        list1.add("国保：碧山寺");
        list1.add("国保：罗睺寺");
        list1.add("省保：殊像寺");
        list1.add("省保：南山寺");
        list1.add("省保：金阁寺");
        list1.add("省保：龙泉寺");
        list1.add("省保：大圆照寺");
        TempleAdapter adapter = new TempleAdapter(list,list1);
        allSimiaoRecyView.setAdapter(adapter);
    }
}
