package com.example.wutai.wutaimoutain.Talk;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Services.services;

import java.util.List;

import javax.microedition.khronos.opengles.GL;

public class GridViewAdapter extends BaseAdapter {
    private static final String TAG = "GridViewAdapter";
    public List<String > list;
    public String pathPrefix;

    public GridViewAdapter(List<String> list) {
        this.list = list;
        pathPrefix=services.urlTalkPicPathPrefix;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        Context context = parent.getContext();
        if(convertView == null){

            convertView = LayoutInflater.from(context).inflate(R.layout.talk_main_grid_view_item,null);

            holder = new Holder();
            holder.imageView=convertView.findViewById(R.id.talk_grid_view_item_square_iv);
            convertView.setTag(holder);
            Glide.with(context).load(pathPrefix+list.get(position)).into(holder.imageView);

        }
        else{
            holder=(Holder) convertView.getTag();
        }

        return convertView;
    }
    class Holder{
        ImageView  imageView;
    }
}
