package com.example.wutai.wutaimoutain.mine;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import android.util.Log;

import com.example.wutai.wutaimoutain.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class UserInfo {
    private static final String TAG = "UserInfo";
    /**
     * name : 1538048746
     * head_pic_stream : 0
     * sex : 男
     * job :
     * signature :
     * introduction :
     */

    private String name;
    private String head_pic_stream;
    private String sex;
    private String job;
    private String signature;
    private String introduction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead_pic_stream() {
        return head_pic_stream;
    }

    public void setHead_pic_stream(String head_pic_stream) {
        this.head_pic_stream = head_pic_stream;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public static Drawable stream2Drawable(String s){
        if(s==null||s.trim().length()<2){
            return null;
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(android.util.Base64.decode(s.getBytes(), android.util.Base64.DEFAULT));
        Drawable drawable =Drawable.createFromStream(bis,"");
        return drawable;
    }
    public static String bitmap2Stream(Bitmap bitmap){
        //第一步:将Bitmap压缩至字节数组输出流ByteArrayOutputStream
        if(bitmap==null){
            return "";
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);
        //第二步:利用Base64将字节数组输出流中的数据转换成字符串String
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        String imageString = new String(Base64.encodeToString(byteArray, Base64.DEFAULT));
        Log.e(TAG, "bitmap2Stream: "+imageString);
        return imageString;
    }
}
