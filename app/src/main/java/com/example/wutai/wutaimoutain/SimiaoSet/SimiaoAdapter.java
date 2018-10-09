package com.example.wutai.wutaimoutain.SimiaoSet;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Simiao_juti.Every_Simiao_Activity;

import java.util.List;

public class SimiaoAdapter extends RecyclerView.Adapter<SimiaoAdapter.BaseViewHolder> {
    private Context mContext;
    private List<Simiao> mData;

    public SimiaoAdapter(Context context, List<Simiao> list) {
        this.mContext = context;
        this.mData = list;
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.simiaoitem, parent, false);
        BaseViewHolder holder = new BaseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        final Simiao data = mData.get(position%mData.size());
        holder.setText(R.id.name_simiao,data.getName());
        Glide.with(mContext).load(data.getImageid()).into(holder.pic_simiao);
        holder.pic_simiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Every_Simiao_Activity.actionstart(mContext,data.getName());
            }
        });
    }
    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }


    class BaseViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView pic_simiao;
        public BaseViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name_simiao);
            pic_simiao = (ImageView)itemView.findViewById(R.id.pic_simiao);
        }

        private void setText(int resLayout,String text){
            name.setText(text);
        }
    }

}
