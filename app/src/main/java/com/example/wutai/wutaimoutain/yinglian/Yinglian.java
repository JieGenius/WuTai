package com.example.wutai.wutaimoutain.yinglian;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Simiao_juti.Simple_text_Activity;
import com.example.wutai.wutaimoutain.init.Query_wu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Yinglian extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<Yinglianbean> yinglians = new ArrayList<>();
    private List<com.example.wutai.wutaimoutain.init.Yinglian> yinglians1;
    private Intent intent;
    private YinglianAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yinglian);
        recyclerView = (RecyclerView)findViewById(R.id.stage_yinglian);
        intent = getIntent();
        if (intent.getStringExtra("dadian")!= null){
            yinglians1 = Query_wu.query_yinglian1(intent.getStringExtra("name"),intent.getStringExtra("dadian"));
        }else if (intent.getStringExtra("dadian")==null&&intent.getStringExtra("name")!= null){
            yinglians1 = Query_wu.query_yinglian(intent.getStringExtra("name"));
        }else yinglians1 = Query_wu.query_yinglian0();
        initlist();


        adapter = new YinglianAdapter(Yinglian.this, yinglians);
        adapter.setHasStableIds(true);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL){
            @Override
            public boolean canScrollHorizontally() {
                return false;
            }
        };
//        MyLinerLayoutManager layoutManager = new MyLinerLayoutManager(Yinglian.this,1, LinearLayoutManager.HORIZONTAL,false);
//        layoutManager.setScrollEnabled(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
    public static void actionstart0(Context context){
        Intent intent = new Intent(context,Yinglian.class);
        context.startActivity(intent);
    }
    public static void actionstart(Context context, String name,String dadian){
        Intent intent = new Intent(context,Yinglian.class);
        intent.putExtra("name",name);
        intent.putExtra("dadian",dadian);
        context.startActivity(intent);
    }
    public static void actionstart1(Context context, String name){
        Intent intent = new Intent(context,Yinglian.class);
        intent.putExtra("name",name);
        context.startActivity(intent);
    }
    public void initlist(){
        yinglians.clear();
        for (com.example.wutai.wutaimoutain.init.Yinglian yinglian:yinglians1){
            yinglians.add(new Yinglianbean(yinglian.getWriter(),yinglian.getSize(),yinglian.getFont(),yinglian.getSimiao(),
                    yinglian.getDadian(),yinglian.getType(),yinglian.getZhiyi(),yinglian.getImgid()));
        }
    }


    public class YinglianAdapter extends RecyclerView.Adapter<YinglianAdapter.ViewHolder> {
        private Intent intent;
        private Context mcontext;
        private List<Yinglianbean> sharesList;
        private  Yinglianbean yinglianbean;
        class ViewHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            Button button;
            public ViewHolder(View view){
                super(view);
                imageView = (ImageView)view.findViewById(R.id.yinglian_window);
                button = (Button)view.findViewById(R.id.maremsg_yinglian);
            }
        }
        public YinglianAdapter(Context mcontext,List<Yinglianbean> newsList){
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.yinglianitem,parent,false);
            ViewHolder holder =new ViewHolder(view);
            holder.setIsRecyclable(false);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(intent);
                }
            });
            return holder;
        }
        @Override
        public void onBindViewHolder(ViewHolder holder, int position){
            yinglianbean = sharesList.get(holder.getLayoutPosition()%getItemCount());
            String writer,size,font,simiao,dadian,type,zhiyi;
            System.out.println(holder.getLayoutPosition()+"now_is_it/////////////");

            Glide.with(mcontext).load(yinglianbean.getImgid()).into(holder.imageView);
            writer = yinglianbean.getWriter();dadian = yinglianbean.getDadian();
            size = yinglianbean.getSize();   simiao = yinglianbean.getSimiao();
            font = yinglianbean.getFont(); type = yinglianbean.getType();
            zhiyi = yinglianbean.getZhiyi();
            intent = new Intent(Yinglian.this,YinglianDetal.class);
            intent.putExtra("writer",writer);
            intent.putExtra("dadian",dadian);
            intent.putExtra("size",size);
            intent.putExtra("simiao",simiao);
            intent.putExtra("font",font);
            intent.putExtra("type",type);
            intent.putExtra("zhiyi",zhiyi);
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
