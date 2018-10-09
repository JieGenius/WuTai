package com.example.wutai.wutaimoutain.Simiao_juti;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;

import java.util.List;

public class JianjieAdapter extends RecyclerView.Adapter<JianjieAdapter.ViewHolder> {
    private Context mcontext;
    private List<Jianjie> sharesList;
    static class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout,neadmove;
        TextView jinajie_name,conttent;
        ImageView fx_pic;
        ImageView iv_pic_bg;
        public ViewHolder(View view){
            super(view);
            iv_pic_bg  = view.findViewById(R.id.iv_pic_bg);
            linearLayout = (LinearLayout)view.findViewById(R.id.setbackground_jianjie);
            neadmove = (LinearLayout)view.findViewById(R.id.need_move);
            jinajie_name = (TextView)view.findViewById(R.id.name_jiejie_simiao);
            conttent = (TextView)view.findViewById(R.id.simiao_jianjietext);
            fx_pic = (ImageView)view.findViewById(R.id.foxiang_pic);
        }
    }
    public JianjieAdapter(Context mcontext,List<Jianjie> newsList){
        this.mcontext = mcontext;
        sharesList = newsList;
    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        if (mcontext == null){
            mcontext = parent.getContext();
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.jianjie_showitem,parent,false);
        final ViewHolder holder =new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        Jianjie jianjie = sharesList.get(position);
        holder.iv_pic_bg.setImageResource(jianjie.getBc_id());
        //holder.linearLayout.setBackgroundResource(jianjie.getBc_id());
        Glide.with(mcontext).load(jianjie.getFxpic_id()).into(holder.fx_pic);
        holder.conttent.setText(jianjie.getContent());
        holder.jinajie_name.setText(jianjie.getName()+"简介：");
    }
    @Override
    public int getItemCount(){
        return sharesList.size();
    }


    public void margin(View v, int l, int t, int r, int b) {
        if (v.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) v.getLayoutParams();
            p.setMargins(l, t, r, b);
            v.requestLayout();
        }
    }
}
