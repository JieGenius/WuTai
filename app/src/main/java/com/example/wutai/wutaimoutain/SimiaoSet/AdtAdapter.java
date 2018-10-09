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

import java.util.List;

public class AdtAdapter extends RecyclerView.Adapter<AdtAdapter.BaseViewHolder> {
    private Context mContext;
    private List<Advertisment> mData;

    public AdtAdapter(Context context, List<Advertisment> list) {
        this.mContext = context;
        this.mData = list;
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.adevertisemet, parent, false);
        BaseViewHolder holder = new BaseViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        Advertisment data = mData.get(position%mData.size());
        Glide.with(mContext).load(data.getImageid()).into(holder.pic_simiao);
    }
    @Override
    public int getItemCount() {
        return mData.size();
    }


    class BaseViewHolder extends RecyclerView.ViewHolder{
        ImageView pic_simiao;
        public BaseViewHolder(View itemView) {
            super(itemView);
            pic_simiao = (ImageView)itemView.findViewById(R.id.advertisments_show);
        }

    }
}
