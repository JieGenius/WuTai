package com.example.wutai.wutaimoutain.Stroy;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.init.Chuanshuo;
import com.example.wutai.wutaimoutain.init.FoJiaowenhua;
import com.example.wutai.wutaimoutain.init.Query_wu;

import java.util.List;

public class Single_stroy_activity extends AppCompatActivity  {
    private TextView title;
    //private TextView editText;
    private TextView chuanshuo1;
    private TextView chuanshuo2;
    private TextView chuanshuo3;
    private TextView fojiao1;
    private TextView fojiao2;
    private TextView fojiao3;
    private TextView fojiao4;
    private Button Button3;
    private Button Button4;

    private ImageView title_show;
    private ImageView src1_chuanshuo1;
    private ImageView src1_chuanshuo2;
    private ImageView src1_chuanshuo3;
    private ImageView src_fojiao1;
    private ImageView src_fojiao2;
    private ImageView src_fojiao3;
    private ImageView src_fojiao4;


    private LinearLayout Lll_1;
    private LinearLayout Lll_2;
    private LinearLayout Lll_3;
    private LinearLayout Lll_4;
    private LinearLayout Lll_5;
    private LinearLayout Lll_6;
    private LinearLayout Lll_7;
    private Intent intent1;
    private  List<FoJiaowenhua> foJiaowenhuas;
    private List<Chuanshuo> chuanshuos;
    private String chuanshuonames[] ={"","",""};
    private int chuanshuopic_id[] ={1,2,3};
    private String foujiaowenhua[] ={"","","",""};
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_stroy_activity);
        initview();
        toolbar = (Toolbar)findViewById(R.id.stroy_toolbar_getin1);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_wuta);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        foJiaowenhuas = Query_wu.query_foujiaowenhua();
        intent1 = getIntent();
        if(intent1.getStringExtra("simiao")!=null){
            getSupportActionBar().setTitle(intent1.getStringExtra("simiao")+"·传说故事");
        }
        else{
            getSupportActionBar().setTitle("传说故事");
        }

        if (intent1.getStringExtra("name")!= null){
            chuanshuos = Query_wu.query_chuanshuo();
            Glide.with(Single_stroy_activity.this).load(R.drawable.picture1).into(title_show);
            for (int i =0;i<chuanshuonames.length&&i<chuanshuos.size();i++){
                chuanshuonames[i] = chuanshuos.get(i).getName();
                chuanshuopic_id[i] = chuanshuos.get(i).getChuanshuopic_id();
            }
        }else{
            if (intent1.getStringExtra("simiao").equals("显通寺")){
                Glide.with(Single_stroy_activity.this).load(R.drawable.temple_xt_iv_icon).into(title_show);
            }else  if (intent1.getStringExtra("simiao").equals("塔院寺")){
                Glide.with(Single_stroy_activity.this).load(R.drawable.temple_tys_legend_icon1).into(title_show);
            }else  if (intent1.getStringExtra("simiao").equals("菩萨顶")){
                Glide.with(Single_stroy_activity.this).load(R.drawable.pusaiding).into(title_show);
            }else  if (intent1.getStringExtra("simiao").equals("碧山寺")){
                Glide.with(Single_stroy_activity.this).load(R.drawable.temple_bs_iv_icon).into(title_show);
            }else  if (intent1.getStringExtra("simiao").equals("殊像寺")){
                Glide.with(Single_stroy_activity.this).load(R.drawable.temple_sx_iv_icon).into(title_show);
            }else  if (intent1.getStringExtra("simiao").equals("南山寺")){
                Glide.with(Single_stroy_activity.this).load(R.drawable.temple_ns_iv_icon).into(title_show);
            }else  if (intent1.getStringExtra("simiao").equals("金阁寺")){
                Glide.with(Single_stroy_activity.this).load(R.drawable.temple_jg_iv_icon).into(title_show);
            }else  if (intent1.getStringExtra("simiao").equals("龙泉寺")){
                Glide.with(Single_stroy_activity.this).load(R.drawable.temple_lq_iv_icon).into(title_show);
            }
            chuanshuos = Query_wu.query_chuanshuo(intent1.getStringExtra("simiao"));
            for (int i =0;i<chuanshuonames.length&&i<chuanshuos.size();i++){
                chuanshuonames[i] = chuanshuos.get(i).getName();
                chuanshuopic_id[i] = chuanshuos.get(i).getChuanshuopic_id();
            }

        }
        setview();

        Lll_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = chuanshuo1.getText().toString();
                Stroy_show_activity_1.actionstart(Single_stroy_activity.this,name,"传说");
            }
        });
        Lll_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = chuanshuo2.getText().toString();
                Stroy_show_activity_1.actionstart(Single_stroy_activity.this,name,"传说");
            }
        });
        Lll_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = chuanshuo3.getText().toString();
                Stroy_show_activity_1.actionstart(Single_stroy_activity.this,name,"传说");
            }
        });
        Lll_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fojiao1.getText().toString();
                Stroy_show_activity_1.actionstart(Single_stroy_activity.this,name,"");
            }
        });
        Lll_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fojiao2.getText().toString();
                Stroy_show_activity_1.actionstart(Single_stroy_activity.this,name,"");
            }
        });
        Lll_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fojiao3.getText().toString();
                Stroy_show_activity_1.actionstart(Single_stroy_activity.this,name,"");
            }
        });
        Lll_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = fojiao4.getText().toString();
                Stroy_show_activity_1.actionstart(Single_stroy_activity.this,name,"");
            }
        });



    }
    public void setview(){
        fojiao1.setText(foJiaowenhuas.get(0).getName());
        fojiao2.setText(foJiaowenhuas.get(1).getName());
        fojiao3.setText(foJiaowenhuas.get(2).getName());
        fojiao4.setText(foJiaowenhuas.get(3).getName());

        Glide.with(Single_stroy_activity.this).load(foJiaowenhuas.get(0).getId_foujiao()).into(src_fojiao1);
        Glide.with(Single_stroy_activity.this).load(foJiaowenhuas.get(1).getId_foujiao()).into(src_fojiao2);
        Glide.with(Single_stroy_activity.this).load(foJiaowenhuas.get(2).getId_foujiao()).into(src_fojiao3);
        Glide.with(Single_stroy_activity.this).load(foJiaowenhuas.get(3).getId_foujiao()).into(src_fojiao4);
        Glide.with(Single_stroy_activity.this).load(chuanshuopic_id[chuanshuopic_id.length-1]).into(src1_chuanshuo1);
        Glide.with(Single_stroy_activity.this).load(chuanshuopic_id[chuanshuopic_id.length-2]).into(src1_chuanshuo2);
        Glide.with(Single_stroy_activity.this).load(chuanshuopic_id[chuanshuopic_id.length-3]).into(src1_chuanshuo3);
        chuanshuo1.setText(chuanshuonames[chuanshuonames.length-1]);
        chuanshuo2.setText(chuanshuonames[chuanshuonames.length-2]);
        chuanshuo3.setText(chuanshuonames[chuanshuonames.length-3]);
        if(chuanshuos.size()==0){
            Lll_1.setVisibility(View.GONE);
            Lll_2.setVisibility(View.GONE);
            Lll_3.setVisibility(View.GONE);
        }
        else if(chuanshuos.size()==1){
            Lll_1.setVisibility(View.GONE);
            Lll_2.setVisibility(View.GONE);
        }
        else if(chuanshuos.size()==2){
            Lll_1.setVisibility(View.GONE);
        }

    }
    public void initview(){
        title=(TextView)findViewById(R.id.title);
        //editText=(TextView)findViewById(R.id.editText);
        chuanshuo1=(TextView)findViewById(R.id.chuanshuo1);
        chuanshuo2=(TextView)findViewById(R.id.chuanshuo2);
        chuanshuo3=(TextView)findViewById(R.id.chuanshuo3);
        fojiao1=(TextView)findViewById(R.id.fojiao1);
        fojiao2=(TextView)findViewById(R.id.fojiao2);
        fojiao3=(TextView)findViewById(R.id.fojiao3);
        fojiao4=(TextView)findViewById(R.id.fojiao4);



        //Button2 = (Button)findViewById(R.id.Button2);
        Button3 = (Button)findViewById(R.id.Button3);
        Button4 = (Button)findViewById(R.id.Button4);




        title_show=(ImageView)findViewById(R.id.title_show);
        src1_chuanshuo1=(ImageView)findViewById(R.id.src1_chuanshuo1);
        src1_chuanshuo2=(ImageView)findViewById(R.id.src1_chuanshuo2);
        src1_chuanshuo3=(ImageView)findViewById(R.id.src1_chuanshuo3);
        src_fojiao1=(ImageView)findViewById(R.id.src_fojiao1);
        src_fojiao2=(ImageView)findViewById(R.id.src_fojiao2);
        src_fojiao3=(ImageView)findViewById(R.id.src_fojiao3);
        src_fojiao4=(ImageView)findViewById(R.id.src_fojiao4);


        Lll_1=(LinearLayout)findViewById(R.id.ll_1);
        Lll_2=(LinearLayout)findViewById(R.id.ll_2);
        Lll_3=(LinearLayout)findViewById(R.id.ll_3);
        Lll_4=(LinearLayout)findViewById(R.id.ll_4);
        Lll_5=(LinearLayout)findViewById(R.id.ll_5);
        Lll_6=(LinearLayout)findViewById(R.id.ll_6);
        Lll_7=(LinearLayout)findViewById(R.id.ll_7);
    }
    public static void actionstart(Context context, String name){
        Intent intent = new Intent(context, Single_stroy_activity.class);
        intent.putExtra("name",name);
        context.startActivity(intent);
    }
    public static void actionstart0(Context context, String simiao){
        Intent intent = new Intent(context, Single_stroy_activity.class);
        intent.putExtra("simiao",simiao);
        context.startActivity(intent);
    }
}
