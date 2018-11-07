package com.example.wutai.wutaimoutain.Talk;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.wutai.wutaimoutain.R;

import java.util.List;

public class CommentsAdapter extends RecyclerView.Adapter <CommentsAdapter.ViewHolder>{

    List<AllTalk.TalkArrBean.CommArrBean>  list;
    LayoutInflater inflater;

    public CommentsAdapter(Context context , List<AllTalk.TalkArrBean.CommArrBean> list){
        this.list = list;
        this.inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.talk_comment_item,parent,false);
        CommentsAdapter.ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AllTalk.TalkArrBean.CommArrBean commArrBean = list.get(position);
        holder.name.setText(commArrBean.getName());
        holder.content.setText(commArrBean.getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView content;
        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.talk_comment_item_name);
            content = itemView.findViewById(R.id.talk_comment_item_content);
        }
    }
}
