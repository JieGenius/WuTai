package com.example.wutai.wutaimoutain.ShareWithOther;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.wutai.wutaimoutain.R;
import com.example.wutai.wutaimoutain.Utils.ScreenUtils;

/**
 * Created by zhangwei on 2018/8/9.
 */

public class PhotoPopwindow extends PopupWindow implements View.OnClickListener{

    private Context mContext;
    private Handler mHandler;
    public PhotoPopwindow(Context mContext, Handler handler) {
        this.mContext = mContext;
        this.mHandler = handler;
        View view = LayoutInflater.from(mContext).inflate(R.layout.popwindow_takephoto, new LinearLayout(mContext), false);
        setContentView(view);
        //设置点击事件
        TextView btnTakePhoto = (TextView) view.findViewById(R.id.btnTakePhoto);
        TextView btnChooseFromAlbum = (TextView) view.findViewById(R.id.btnChooseFromAlbum);
        TextView btnCancle = (TextView) view.findViewById(R.id.btnCancle);
        btnTakePhoto.setOnClickListener(this);
        btnChooseFromAlbum.setOnClickListener(this);
        btnCancle.setOnClickListener(this);

        //获取宽高
        //设置宽高
        setWidth(ScreenUtils.getScreenWidth(mContext));
//        setWidth(ScreenUtils.getDeviceWidth(mContext));
        setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        setBackgroundDrawable(new BitmapDrawable());
        //设置外部点击消失
        setOutsideTouchable(true);
        setFocusable(true);
        //设置动画
        setAnimationStyle(R.style.take_photo_anim);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTakePhoto:
                mHandler.sendEmptyMessage(110);
                break;
            case R.id.btnChooseFromAlbum:
                mHandler.sendEmptyMessage(111);
                break;
            case R.id.btnCancle:
                this.dismiss();
                break;
            default:
                break;
        }
    }

}


