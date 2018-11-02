package com.example.wutai.wutaimoutain.yinglian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Review_activity;
import com.example.wutai.wutaimoutain.init.Query_wu;
import com.example.wutai.wutaimoutain.init.Shiyi;

import java.util.ArrayList;

public class YinglianDetal extends AppCompatActivity {
    TextView writer,size,font,zhiyi,type,simiao,dadain;
    ImageView shipai_pic;
    private Intent intent;
    private android.support.v7.widget.Toolbar toolbar;
    private RecyclerView recyclerView;
    private ShiyiAdapter shiyiAdapter;
    private ArrayList<Shiyi> shiyis;
    private ImageView imgview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yinglian_detal);
        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.yinglian_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_wuta);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
        writer = (TextView)findViewById(R.id.writer_yinglian);
        size = (TextView)findViewById(R.id.size_yinglian);
        font = (TextView)findViewById(R.id.font_yinglian);
        zhiyi =(TextView)findViewById(R.id.zhiyi_yinglian);
        type = (TextView)findViewById(R.id.type_yinglian);
        simiao = (TextView)findViewById(R.id.simiao_yinglian);
        dadain = (TextView)findViewById(R.id.dadian_yinglian);
        shipai_pic = (ImageView)findViewById(R.id.shipa_yinglian);
        imgview = (ImageView)findViewById(R.id.more_review);
        imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(YinglianDetal.this, Review_activity.class);
               startActivity(intent);
            }
        });
        intent = getIntent();
        System.out.println(intent.getStringExtra("simiao")+ intent.getStringExtra("dadian"));
        shiyis =(ArrayList<Shiyi>) Query_wu.query_shiyi(intent.getStringExtra("simiao"),
                intent.getStringExtra("dadian"));
        writer.setText(intent.getStringExtra("writer"));
        size.setText(intent.getStringExtra("size"));
        font.setText(intent.getStringExtra("font"));
        zhiyi.setText(intent.getStringExtra("zhiyi"));
        type.setText(intent.getStringExtra("type"));
        simiao.setText(intent.getStringExtra("simiao"));
        dadain.setText(intent.getStringExtra("dadian"));
        recyclerView = (RecyclerView)findViewById(R.id.shiyi_show);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        setadapter(shiyis);
        Glide.with(YinglianDetal.this).load(R.drawable.shipai_yl).into(shipai_pic);
    }
    public void setadapter(ArrayList<Shiyi> shiyis1){
        shiyiAdapter = new ShiyiAdapter(YinglianDetal.this,shiyis1);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(YinglianDetal.this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(shiyiAdapter);
    }
}
