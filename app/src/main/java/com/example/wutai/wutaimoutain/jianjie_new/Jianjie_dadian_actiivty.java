package com.example.wutai.wutaimoutain.jianjie_new;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Simiao_juti.Every_Simiao_Activity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaBaiTaActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaCangJingGeActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.DaCiYanShouBaoDianActivity;
import com.example.wutai.wutaimoutain.TaYuanSiVoice.TianWangDianActivity;
import com.example.wutai.wutaimoutain.Utils.GridSpacingItemDecoration;
import com.example.wutai.wutaimoutain.Utils.MyLogUtils;
import com.example.wutai.wutaimoutain.init.Dadian;
import com.example.wutai.wutaimoutain.init.Query_wu;
import com.example.wutai.wutaimoutain.yinglian.YinglianShowActivity;
import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Jianjie_dadian_actiivty extends AppCompatActivity {
    private static final String TAG = "Jianjie_dadian_actiivty";
    private TextView jianjie_newcontent;
    private Toolbar toolbar;
    private Intent getintent;
    private List<Dadian> dadians;
    private ArrayList<Traver_simiao_pic> arrayList = new ArrayList<>();
    private String simaio;
    private String dadian;
    private Dadian dadiandemo;
    private FloatingActionButton more,yunyin,yinglian;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jianjie_dadian_actiivty);
        initview();
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_wuta);
        tabLayout.addTab(tabLayout.newTab().setText("五台山"));
        tabLayout.addTab(tabLayout.newTab().setText("文殊殿"));

        getintent = getIntent();
        simaio = getintent.getStringExtra("simiao");
        dadian = getintent.getStringExtra("dadian");
        MyLogUtils.e("simiao :" + simaio + "dadian ： " + dadian );
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Every_Simiao_Activity.actionstart(Jianjie_dadian_actiivty.this,simaio);
            }
        });
        dadians = Query_wu.query_dadian(simaio);

        final MyViewPageAdapter viewPageAdapter = new MyViewPageAdapter();
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setTabsFromPagerAdapter(viewPageAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        initlist();
       /* more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu pop = new PopupMenu(Jianjie_dadian_actiivty.this, more);//v是加号控件
                if (simaio.equals("塔院寺")){
                pop.getMenuInflater().inflate(R.menu.tayaunsimenu, pop.getMenu());
                pop.show();
                pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.action_all_baita:
                                Jianjie_dadian_actiivty.actionstart1(Jianjie_dadian_actiivty.this,"塔院寺","大白塔");
                                break;
                            case R.id.action_all_cangjing:
                                Jianjie_dadian_actiivty.actionstart1(Jianjie_dadian_actiivty.this,"塔院寺","藏经阁");
                                break;
                            case R.id.action_all_ciyan:
                                Jianjie_dadian_actiivty.actionstart1(Jianjie_dadian_actiivty.this,"塔院寺","大慈延寿宝殿");
                                break;
                            case R.id.action_all_tianwang:
                                Jianjie_dadian_actiivty.actionstart1(Jianjie_dadian_actiivty.this,"塔院寺","天王殿");
                                break;
                        }
                        return true;
                    }
                });
               }else if (simaio.equals("显通寺")){

                    pop.getMenuInflater().inflate(R.menu.xaintongmenu, pop.getMenu());
                    pop.show();
                    pop.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.action_all_gunyin:
                                    Jianjie_dadian_actiivty.actionstart1(Jianjie_dadian_actiivty.this,"显通寺","观音殿");
                                    break;
                                case R.id.action_all_daxiong:
                                    Jianjie_dadian_actiivty.actionstart1(Jianjie_dadian_actiivty.this,"显通寺","大雄宝殿");
                                    break;
                                case R.id.action_all_wenshu:
                                    Jianjie_dadian_actiivty.actionstart1(Jianjie_dadian_actiivty.this,"显通寺","大文殊殿");
                                    break;
                            }
                            return true;
                        }
                    });


                }else Toast.makeText(Jianjie_dadian_actiivty.this,"暂无数据抱歉",Toast.LENGTH_SHORT).show();
            }
        });*/

        // 根据寺庙的名字和大殿名字 来确定跳转到哪个 页面
        /*yunyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(simaio.equals("塔院寺")){
                        if(dadian.equals("大白塔")){
                            startActivity(new Intent(Jianjie_dadian_actiivty.this, DaBaiTaActivity.class));
                        }
                        else if(dadian.equals("藏经阁")){
                            startActivity(new Intent(Jianjie_dadian_actiivty.this, DaCangJingGeActivity.class));
                        }
                        else if(dadian.equals("大慈延寿宝殿")){
                            startActivity(new Intent(Jianjie_dadian_actiivty.this, DaCiYanShouBaoDianActivity.class));
                        }
                        else if(dadian.equals("天王殿")){
                            startActivity(new Intent(Jianjie_dadian_actiivty.this, TianWangDianActivity.class));
                        }
                    }
            }
        });


        yinglian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YinglianShowActivity.actionstart(Jianjie_dadian_actiivty.this,simaio,dadian);
            }
        });
*/

    }

    public void initview(){
        /*more = (FloatingActionButton)findViewById(R.id.more_dadian_ben);
        yunyin = (FloatingActionButton)findViewById(R.id.yunyin_jiehsuo_ben);
        more = (FloatingActionButton)findViewById(R.id.more_dadian_ben);
        yunyin = (FloatingActionButton)findViewById(R.id.yunyin_jiehsuo_ben);
        yinglian = (FloatingActionButton)findViewById(R.id.yinglian_jiehsuo_ben);*/
        toolbar = findViewById(R.id.jianjie_dadian_toolbar);
        tabLayout = findViewById(R.id.hall_tab_layout);
        viewPager = findViewById(R.id.hall_view_pager);
    }


    public void initlist(){
        arrayList.clear();
        arrayList.add(new Traver_simiao_pic(R.drawable.traverpic2,simaio,dadian ));
        arrayList.add(new Traver_simiao_pic(R.drawable.traverpic3,simaio,dadian));
        arrayList.add(new Traver_simiao_pic(R.drawable.traverpic4,simaio,dadian));
        arrayList.add(new Traver_simiao_pic(R.drawable.traverpic5,simaio,dadian));
        arrayList.add(new Traver_simiao_pic(R.drawable.traverpic6,simaio,dadian));
    }

    public static void actionstart1(Context context, String name,String dadian){
        Intent intent = new Intent(context, Jianjie_dadian_actiivty.class);
        intent.putExtra("dadian",dadian);
        intent.putExtra("simiao",name);
        context.startActivity(intent);
    }


//    public static void actionstart2(Context context, int id,String name,String dadian){
//        Intent intent = new Intent(context, Jianjie_dadian_actiivty.class);
//        intent.putExtra("id",id);
//        intent.putExtra("dadian",dadian);
//        intent.putExtra("simiao",name);
//        context.startActivity(intent);
//    }










    public class New_dadain_Adapter extends RecyclerView.Adapter<New_dadain_Adapter.ViewHolder> {

        private Context mcontext;
        private ArrayList<Traver_simiao_pic> sharesList;
        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            public ViewHolder(View view){
                super(view);
                imageView = (ImageView)view.findViewById(R.id.pic_traver);
            }
        }
        public New_dadain_Adapter(Context mcontext,ArrayList<Traver_simiao_pic> newsList){
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.travepic,parent,false);
            final ViewHolder holder =new ViewHolder(view);

            return holder;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            final Traver_simiao_pic traver_simiao_pic = sharesList.get(position);
            Glide.with(mcontext).load(traver_simiao_pic.getPic_id()).into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Glide.with(Jianjie_dadian_actiivty.this).load(traver_simiao_pic.getPic_id()).into(new_jianjie_stage);
                }
            });
//        if (jianjie.isIssimiao()){
//            holder.fx_pic.setVisibility(View.GONE);
//            margin(holder.neadmove,0,1350,0,0);
//        }else
        }

        @Override
        public int getItemCount(){
            return sharesList.size();
        }
    }

    class MyViewPageAdapter extends PagerAdapter{
        List<View> views;
        LayoutInflater layoutInflater;
        public MyViewPageAdapter() {
            super();
            views = new ArrayList<>(dadians.size());
            layoutInflater = getLayoutInflater();
            for(int i=0;i<dadians.size();i++){
                View view =layoutInflater.inflate(R.layout.hall_view_page_item,null);
                ImageView jianjie_title = view.findViewById(R.id.jiejie_pic_dadain);
                ImageView  new_jianjie_stage = view.findViewById(R.id.jianjie_dadian_stage);
                RecyclerView trave_pics = view.findViewById(R.id.travler_dadian_pics);
                ImageView dadian_fenbu = view.findViewById(R.id.jianjie_dadianfenbu);
                TextView jianjie_newcontent = view.findViewById(R.id.jianjie_dadian_content);
                ImageView fxdata = view.findViewById(R.id.jianjie_foxiangjieshao);


                New_dadain_Adapter adapter = new New_dadain_Adapter(Jianjie_dadian_actiivty.this,arrayList);
                StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
                trave_pics.setLayoutManager(layoutManager);
                trave_pics.setAdapter(adapter);
                int spanCount = 20; // 3 columns
                int spacing = 20; // 50px
                boolean includeEdge = false;
                trave_pics.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));

                Glide.with(Jianjie_dadian_actiivty.this).load(dadians.get(i).getTitle_imgid()).into(jianjie_title);
                Glide.with(Jianjie_dadian_actiivty.this).load(dadians.get(i).getDadianjianjie_imgid()).into(new_jianjie_stage);
                Glide.with(Jianjie_dadian_actiivty.this).load(dadians.get(i).getFouxiang_imgid()).into(dadian_fenbu);
                Glide.with(Jianjie_dadian_actiivty.this).load(dadians.get(i).getData_fouxaing()).into(fxdata);
                String content = dadians.get(i).getJianjie_dadian().replace(" ","");
                jianjie_newcontent.setText(content);
                views.add(view);
            }
        }

        @Override
        public int getCount() {
            return views.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(views.get(position));
            return views.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(views.get(position));
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return dadians.get(position).getName();
        }
    }

}
