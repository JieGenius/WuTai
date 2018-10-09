package com.example.wutai.wutaimoutain.sendTalk;


import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wutai.wutaimoutain.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ImageGridAdapter extends BaseAdapter {

    private TextCallback textcallback = null;

    final String TAG = getClass().getSimpleName();

    Activity act;

    List<ImageItem> dataList;

    Map<String, String> map = new HashMap<String, String>();

    BitmapCache cache;

    private Handler mHandler;

    private int selectTotal = 0;

    BitmapCache.ImageCallback callback = new BitmapCache.ImageCallback() {
        @Override
        public void imageLoad(ImageView imageView, Bitmap bitmap, Object... params) {
            if (imageView != null && bitmap != null) {
                String url = (String) params[0];
                if (url != null && url.equals((String) imageView.getTag())) {
                    ((ImageView) imageView).setImageBitmap(bitmap);
                }
            }
        }
    };

    public static interface TextCallback {
        public void onListen(int count);
    }

    public void setTextCallback(TextCallback listener) {
        textcallback = listener;
    }

    public ImageGridAdapter(Activity act, List<ImageItem> list, Handler mHandler) {
        this.act = act;
        dataList = list;
        cache = new BitmapCache();
        this.mHandler = mHandler;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (dataList != null) {
            count = dataList.size();
        }
        return count;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    class Holder {
        private ImageView iv;

        private ImageView selected;

        private TextView text;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final Holder holder;

        if (convertView == null) {
            holder = new Holder();
            convertView = View.inflate(act, R.layout.talk_album_item_grid_view_item, null);
            holder.iv = (ImageView) convertView.findViewById(R.id.image);
            holder.selected = (ImageView) convertView.findViewById(R.id.isselected);
            holder.text = (TextView) convertView.findViewById(R.id.item_image_grid_text);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        final ImageItem item = dataList.get(position);

        holder.iv.setTag(item.imagePath);
        cache.displayBmp(holder.iv, item.thumbnailPath, item.imagePath, callback);
        if (item.isSelected) {
            holder.selected.setImageResource(R.drawable.talk_icon_data_select);
            holder.text.setBackgroundResource(R.drawable.talk_bgd_relatly_line);
        } else {
            holder.selected.setImageResource(-1);
            holder.text.setBackgroundColor(0x00000000);
        }
        holder.iv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                String path = dataList.get(position).imagePath;
//                if ((Bimp.drr.size() + selectTotal) < 9)
//                {
                item.isSelected = !item.isSelected;
                if (item.isSelected) {

                    if(Bimp.drr.size()+selectTotal>=9){
                        Toast.makeText(act,"最多只能选9张照片",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        holder.selected.setImageResource(R.drawable.talk_icon_data_select);
                        holder.text.setBackgroundResource(R.drawable.talk_bgd_relatly_line);
                        selectTotal++;
                        if (textcallback != null) textcallback.onListen(selectTotal);
                        map.put(path, path);
                    }
                } else if (!item.isSelected) {
                    holder.selected.setImageResource(-1);
                    holder.text.setBackgroundColor(0x00000000);
                    selectTotal--;
                    if (textcallback != null) textcallback.onListen(selectTotal);
                    map.remove(path);
                }
            }

        });

        return convertView;
    }
}
