package com.example.wutai.wutaimoutain.Simiao_juti;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Stroy.Single_stroy_activity;
import com.example.wutai.wutaimoutain.Utils.GridSpacingItemDecoration;
import com.example.wutai.wutaimoutain.VR.VR_showactivity;
import com.example.wutai.wutaimoutain.allsimiaos.Allsimiaoyilan_test;
import com.example.wutai.wutaimoutain.allsimiaos.MoveDBFile;
import com.example.wutai.wutaimoutain.init.Query_wu;
import com.example.wutai.wutaimoutain.init.SimiaoItem;
import com.example.wutai.wutaimoutain.jianjie_new.Jianjie_dadian_actiivty;
import com.example.wutai.wutaimoutain.jianjie_new.Jianjie_new_activity;
import com.example.wutai.wutaimoutain.jianjie_new.Traver_pic;
import com.example.wutai.wutaimoutain.navigation.BiShanSiActivity;
import com.example.wutai.wutaimoutain.navigation.LuoHouSiActivity;
import com.example.wutai.wutaimoutain.navigation.PuSaDingActivity;
import com.example.wutai.wutaimoutain.navigation.TaYuanSiActivity;
import com.example.wutai.wutaimoutain.navigation.XianTongSiActivity;
import com.example.wutai.wutaimoutain.yinglian.Yinglian;
import com.example.wutai.wutaimoutain.yinglian.YinglianShowActivity;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class Every_Simiao_Activity extends AppCompatActivity {
    private FloatingActionButton ev_jian,ev_couplets,ev_daohang,ev_stroy,ev_vr,ev_yunyin;
    private TextView name_ev_simiao,per_num,name_simiao;
    private CircleImageView simaio_circleimg;
    private RelativeLayout relativeLayout;
    private RecyclerView recyclerView;
    private String mname= "";
    private ArrayList<SimiaoItem> simiaoItems;
    private SimiaoItem simiaoItem;
    private Intent main_intent;
    private ArrayList<Traver_pic> arrayList = new ArrayList<>();
    private New_picAdapter1 adapter1;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singel_simiao_);
        main_intent = getIntent();
        final String name_simiao = main_intent.getStringExtra("name");
        simiaoItems =(ArrayList<SimiaoItem>) Query_wu.query_simiao(name_simiao);
        for (SimiaoItem simiaoItem1:simiaoItems){
            if (simiaoItem1.getName().equals(name_simiao)){
                simiaoItem = simiaoItem1;
                break;
            }

        }
        init(name_simiao);
        toolbar = (Toolbar)findViewById(R.id.singlesimiao_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(simiaoItem.getName());
        toolbar.setNavigationIcon(R.drawable.ic_back_wuta);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initdata();
        initlist();
        adapter1 = new New_picAdapter1(Every_Simiao_Activity.this,arrayList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setAdapter(adapter1);
        recyclerView.setLayoutManager(layoutManager);
        int spanCount = 20; // 3 columns
        int spacing = 20; // 50px
        boolean includeEdge = false;
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

        ev_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Jianjie_new_activity.actionstart1(Every_Simiao_Activity.this,name_simiao);
            }
        });
        ev_couplets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YinglianShowActivity.actionstart1(Every_Simiao_Activity.this,name_simiao);
            }
        });
        ev_stroy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Single_stroy_activity.actionstart0(Every_Simiao_Activity.this,name_simiao);
            }
        });
        ev_vr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VR_showactivity.actionstart0(Every_Simiao_Activity.this,name_simiao);
            }
        });
        ev_yunyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//大殿简介
                Jianjie_dadian_actiivty.actionstart1(Every_Simiao_Activity.this,name_simiao,name_simiao);
                /*if(name_simiao.equals("显通寺")||name_simiao.equals("菩萨顶")||name_simiao.equals("碧山寺")||name_simiao.equals("塔院寺")||name_simiao.equals("罗睺寺")){
                    Jianjie_dadian_actiivty.actionstart1(Every_Simiao_Activity.this,name_simiao,name_simiao);
                }
                else{
                    Toast.makeText(Every_Simiao_Activity.this,"暂无该殿数据",Toast.LENGTH_SHORT).show();
                }*/


            }
        });
        ev_daohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//导航解说
                if (name_simiao.equals("塔院寺"))
                {
                    Intent intent = new Intent(Every_Simiao_Activity.this, TaYuanSiActivity.class);
                    startActivity(intent);
                }
                else if(name_simiao.equals("显通寺")){
                    Intent intent = new Intent(Every_Simiao_Activity.this, XianTongSiActivity.class);
                    startActivity(intent);
                }
                else if(name_simiao.equals("菩萨顶")){
                    Intent intent = new Intent(Every_Simiao_Activity.this, PuSaDingActivity.class);
                    startActivity(intent);
                }
                else if(name_simiao.equals("碧山寺")){
                    Intent intent = new Intent(Every_Simiao_Activity.this, BiShanSiActivity.class);
                    startActivity(intent);
                }
                else if(name_simiao.equals("罗睺寺")){
                    Intent intent = new Intent(Every_Simiao_Activity.this, LuoHouSiActivity.class);
                    startActivity(intent);
                }
                else Toast.makeText(Every_Simiao_Activity.this,"暂时没有数据",Toast.LENGTH_SHORT).show();

            }
        });
    }
    public void init(String name){
        ev_couplets = (FloatingActionButton)findViewById(R.id.couplets_every);
        ev_daohang = (FloatingActionButton)findViewById(R.id.navigation_every);
        ev_jian = (FloatingActionButton)findViewById(R.id.simple_text_every);
        ev_stroy =(FloatingActionButton)findViewById(R.id.stroy_every);
        ev_vr =(FloatingActionButton)findViewById(R.id.vr_every);
        ev_yunyin = (FloatingActionButton)findViewById(R.id.yunyinjieshuo_every);
        name_ev_simiao = (TextView)findViewById(R.id.name_simiao_view);
        name_simiao = (TextView)findViewById(R.id.nameofsimiao_ev);
        simaio_circleimg = (CircleImageView)findViewById(R.id.pic_simiaode_ev);
        per_num = (TextView)findViewById(R.id.persion_num_simiao);
        recyclerView = (RecyclerView) findViewById(R.id.ev_view_simiao);
        relativeLayout = (RelativeLayout)findViewById(R.id.back_ev_simiao);
        if (name.equals("显通寺")){
            mname = "显通寺";
        }
    }
    public void initlist(){
        arrayList.clear();
        arrayList.add(new Traver_pic(R.drawable.traverdadian2,""));
        arrayList.add(new Traver_pic(R.drawable.traverdadain3,""));
        arrayList.add(new Traver_pic(R.drawable.traverdadian8,""));
        arrayList.add(new Traver_pic(R.drawable.traverpic6,""));
        arrayList.add(new Traver_pic(R.drawable.traverpic2,""));

    }
    public void initdata(){
        per_num.setText("访问人数："+simiaoItem.getNum_persion());
        name_ev_simiao.setText(simiaoItem.getName()+"风光：");
        name_simiao.setText(simiaoItem.getName());
        Glide.with(Every_Simiao_Activity.this).load(simiaoItem.getCircle_imgid()).into(simaio_circleimg);
        relativeLayout.setBackgroundResource(simiaoItem.getBack_imgid());
    }
    public static void actionstart(Context context,String name,int backid){
        Intent intent = new Intent(context,Every_Simiao_Activity.class);
        intent.putExtra("name",name);
        intent.putExtra("param1",backid);
        context.startActivity(intent);
    }
    public static void actionstart(Context context,String name){
        Intent intent = new Intent(context,Every_Simiao_Activity.class);
        intent.putExtra("name",name);
        context.startActivity(intent);
    }




    public class New_picAdapter1 extends RecyclerView.Adapter<New_picAdapter1.ViewHolder> {
        private Context mcontext;
        private ArrayList<Traver_pic> sharesList;
        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            public ViewHolder(View view){
                super(view);
                imageView = (ImageView)view.findViewById(R.id.img_show);
            }
        }
        public New_picAdapter1(Context mcontext,ArrayList<Traver_pic> newsList){
            this.mcontext = mcontext;
            sharesList = newsList;
        }

        @Override
        public int getItemViewType(int position) {
            return position;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
            if (mcontext == null){
                mcontext = parent.getContext();
            }
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.showimg_test,parent,false);
            final ViewHolder holder =new ViewHolder(view);

            return holder;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            final Traver_pic traver_pic = sharesList.get(position);
            Glide.with(mcontext).load(traver_pic.getPic_id()).into(holder.imageView);
        }
        @Override
        public int getItemCount(){
            return sharesList.size();
        }
    }
}
