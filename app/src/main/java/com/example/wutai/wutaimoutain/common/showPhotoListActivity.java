package com.example.wutai.wutaimoutain.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Services.services;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;

public class showPhotoListActivity extends Activity {
    public static List<View> list1;
    public static List<String> picPath;
    ViewPager viewPager;
    LayoutInflater inflater ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_show_pic_activity);
        inflater = getLayoutInflater();
        viewPager =findViewById(R.id.common_show_pic_view_pager);
        initView();
        ShowPhotoPagerAdapter pagerAdapter = new ShowPhotoPagerAdapter(list1);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(getIntent().getIntExtra("position",0));
    }

    private void initView() {
        list1 = new ArrayList<>();
        for(int i=0;i<picPath.size();i++){
            View view = inflater.inflate(R.layout.activity_one_pic_preview,null);
            PhotoView imageView = view.findViewById(R.id.com_one_pic_photo_view);
            //PhotoView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
            Glide.with(this).load(services.urlTalkPicPathPrefix+picPath.get(i)).into(imageView);
            list1.add(view);
        }
    }

    public static void newInstance(Context context,int postion,List<String> list){
        Intent intent = new Intent(context,showPhotoListActivity.class);
        picPath = list;
        intent.putExtra("position",postion);
        context.getApplicationContext().startActivity(intent);
    }
    class ShowPhotoPagerAdapter extends PagerAdapter{
        List<View> list;

        public ShowPhotoPagerAdapter(List<View> list) {
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(list.get(position));
            return list.get(position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView(list.get(position));
        }
    }

}
