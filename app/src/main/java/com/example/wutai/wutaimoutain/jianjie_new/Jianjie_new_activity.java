package com.example.wutai.wutaimoutain.jianjie_new;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Utils.GridSpacingItemDecoration;
import com.example.wutai.wutaimoutain.init.Query_wu;
import com.example.wutai.wutaimoutain.init.SimiaoItem;
import com.example.wutai.wutaimoutain.yinglian.YinglianShowActivity;

import java.util.ArrayList;
import java.util.List;

public class Jianjie_new_activity extends AppCompatActivity {
    public ImageView jianjie_title,new_jianjie_stage;
    private RecyclerView trave_pics;
    private TextView jianjie_newcontent;
    private Toolbar toolbar;
    private Intent getintent;
    private SimiaoItem simiaoItem;
    private List<SimiaoItem> simiaoItems;
    private New_picAdapter adapter;
    private ArrayList<Traver_pic> arrayList = new ArrayList<>();
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jianjie_new_activity);
        initview();
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_back_wuta);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getintent = getIntent();
        if(getintent.getStringExtra("name")!=null){
            getSupportActionBar().setTitle(getintent.getStringExtra("name")+"·简介");
        }else{
            getSupportActionBar().setTitle("简介");
        }
        simiaoItems = Query_wu.query_simiao(getintent.getStringExtra("name"));
        simiaoItem = simiaoItems.get(0);
        name = simiaoItem.getName();
        initlist();
        adapter = new New_picAdapter(Jianjie_new_activity.this,arrayList);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL);
        trave_pics.setLayoutManager(layoutManager);
        trave_pics.setAdapter(adapter);
        int spanCount = 20; // 3 columns
        int spacing = 20; // 50px
        boolean includeEdge = false;
        trave_pics.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        setview();

    }
    public void setview(){
        Glide.with(Jianjie_new_activity.this).load(simiaoItem.getTitle_img()).into(jianjie_title);
        Glide.with(Jianjie_new_activity.this).load(simiaoItem.getJianjie_imgid()).into(new_jianjie_stage);
        String content = simiaoItem.getJianjie().replace(" ","");
        jianjie_newcontent.setText(content);

    }
    public void initview(){
        jianjie_title = (ImageView)findViewById(R.id.jianjie_pic_simaio);
        new_jianjie_stage = (ImageView)findViewById(R.id.jianjie_pic_stage);
        trave_pics = (RecyclerView)findViewById(R.id.travler_pics);
        jianjie_newcontent = (TextView)findViewById(R.id.jianjie_new_content);
        toolbar = (Toolbar) findViewById(R.id.jianjie_new_toolbar);
    }

    public void initlist(){
        arrayList.clear();

        arrayList.add(new Traver_pic(R.drawable.traverdadian2,name));
        arrayList.add(new Traver_pic(R.drawable.traverdadain3,name));
        arrayList.add(new Traver_pic(R.drawable.traverdadian8,name));
        arrayList.add(new Traver_pic(R.drawable.traverpic6,name));
        arrayList.add(new Traver_pic(R.drawable.traverpic2,name));

    }


    public static void actionstart1(Context context, String name){
        Intent intent = new Intent(context, Jianjie_new_activity.class);
        intent.putExtra("name",name);
        context.startActivity(intent);
    }


    public static void actionstart2(Context context, int id,String name){
        Intent intent = new Intent(context, Jianjie_new_activity.class);
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        context.startActivity(intent);
    }

    /*
     * 适配气代码
     *
     *
     *
     * */



    public class New_picAdapter extends RecyclerView.Adapter<New_picAdapter.ViewHolder> {
        private Context mcontext;
        private ArrayList<Traver_pic> sharesList;
        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            public ViewHolder(View view){
                super(view);
                imageView = (ImageView)view.findViewById(R.id.pic_traver);
            }
        }
        public New_picAdapter(Context mcontext,ArrayList<Traver_pic> newsList){
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
            final Traver_pic traver_pic = sharesList.get(position);
            Glide.with(mcontext).load(traver_pic.getPic_id()).into(holder.imageView);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Glide.with(Jianjie_new_activity.this).load(traver_pic.getPic_id()).into(new_jianjie_stage);
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
}
