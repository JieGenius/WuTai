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
                finish();
            }
        });
        dadians = Query_wu.query_dadian(simaio);

        final MyViewPageAdapter viewPageAdapter = new MyViewPageAdapter();
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setTabsFromPagerAdapter(viewPageAdapter);
        tabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        initlist();
    }

    public void initview(){
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
