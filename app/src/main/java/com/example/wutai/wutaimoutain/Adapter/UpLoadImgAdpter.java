package com.example.wutai.wutaimoutain.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;

import java.util.List;

/**
 * Created by zhangwei on 2018/8/9.
 */

public class UpLoadImgAdpter extends BaseAdapter{
    private Context mContex;
    private int mMaxPosition;
    private List<String> mList;
    private ViewHolder vh;

    public UpLoadImgAdpter(Context mContex, List<String> mList) {
        this.mContex = mContex;
        this.mList = mList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        vh = null;
        // 对 ViewHolder 中的控件 获取
        if (convertView == null){
            vh = new ViewHolder();
            convertView= LayoutInflater.from(mContex).inflate(R.layout.grid_item,parent,false);
            vh.img= (ImageView) convertView.findViewById(R.id.imageView1);
//            vh.demimg= (ImageView) convertView.findViewById(R.id.delimg);
            convertView.setTag(vh);
        }else{
            vh = (ViewHolder) convertView.getTag();
        }

        if (position==mMaxPosition-1){ //说明要显示
            Glide.with(mContex).load(R.mipmap.add_picture).dontAnimate()
                    .centerCrop().into(vh.img);
            vh.img.setVisibility(View.VISIBLE);
//            vh.demimg.setVisibility(View.GONE);
            if (position==5 && mMaxPosition == 6){//设置最大6个。那么达到最大，就隐藏。
//                vh.img.setImageResource(R.drawable.id_photo);
                vh.img.setVisibility(View.GONE);
            }
        }else{//设置图片。
//            vh.demimg.setVisibility(View.VISIBLE);
//            Glide.with(mContex).load(mList.get(position).get("itemImage")).asBitmap().into(vh.img);     //设置
            Glide.with(mContex).load(mList.get(position)).into(vh.img);
        }

        return convertView;
    }

    @Override
    public int getCount() {
        mMaxPosition = mList.size()+1;
        return mMaxPosition;
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    // 添加 Item 的控件
    public class ViewHolder{
        public ImageView img;
    }

}
