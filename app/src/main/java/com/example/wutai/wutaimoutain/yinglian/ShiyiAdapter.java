package com.example.wutai.wutaimoutain.yinglian;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.init.Shiyi;

import java.util.ArrayList;
import java.util.List;

public class ShiyiAdapter extends RecyclerView.Adapter <ShiyiAdapter.ViewHolder> {
    private Context mcontext;
    private ArrayList<Shiyi> sharesList;
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView1,textView2,textView3;
        public ViewHolder(View view){
            super(view);
            textView1 = (TextView) view.findViewById(R.id.shiyi_name);
            textView2 = (TextView) view.findViewById(R.id.content_shiyi);
            textView3 = (TextView) view.findViewById(R.id.sourec_shiyi);
        }
    }
    public ShiyiAdapter(Context mcontext,ArrayList<Shiyi> newsList){
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shiyiitem,parent,false);
        final ViewHolder holder =new ViewHolder(view);

        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Shiyi shiyiItem = sharesList.get(position%getItemCount());
        holder.textView1.setText(shiyiItem.getName());
        holder.textView2.setText(shiyiItem.getContent());
        holder.textView3.setText(shiyiItem.getSrc());
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
