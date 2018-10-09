package com.example.wutai.wutaimoutain.ManYouWuTai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.wutai.wutaimoutain.R;
import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class listAdapter extends BaseAdapter {
    List<Object>lists;
    LayoutInflater inflater;
    Context context;

    public listAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(position==0){
            convertView = inflater.inflate(R.layout.image,null);
            ImageView imageView = convertView.findViewById(R.id.iv_pic_top);
            imageView.setImageResource(R.drawable.roam_wu_tai_top_iv);
            imageView.setPadding(0,0,0,20);
            return convertView;
        }
        convertView = null;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_item,null);
            holder.title = convertView.findViewById(R.id.tv_title);
            holder.tv_abstract = convertView.findViewById(R.id.tv_abstract);
            holder.pic = convertView.findViewById(R.id.iv_pic);
            holder.head = convertView.findViewById(R.id.civ_head);
            holder.name = convertView.findViewById(R.id.tv_name);
            holder.num_comm = convertView.findViewById(R.id.tv_num_commu);
            holder.num_read = convertView.findViewById(R.id.tv_num_read);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        Data data = (Data)lists.get(position);
        holder.title.setText(data.title);
        holder.tv_abstract.setText(data.abst);
        holder.name.setText(data.name);
        holder.num_read.setText("阅读："+data.num_read);
        holder.num_comm.setText("评论："+data.num_commu);

        Glide.with(context).load(data.pic).into(holder.pic);

        return convertView;
    }
    class ViewHolder{
        TextView title;
        TextView tv_abstract;
        ImageView pic;
        CircleImageView head;
        TextView name;
        TextView num_comm;
        TextView num_read;
    }
}
