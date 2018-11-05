package com.example.wutai.wutaimoutain.init;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import org.litepal.LitePal;
import com.example.wutai.wutaimoutain.R;

import java.io.File;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

public class Setall {
    private static final String TAG = "Setall";
    static private String dbPath="/data/data/com.example.wutai.wutaimoutain/databases/wutaishan.db";
    static private String srcPath="/data/data/com.example.wutai.wutaimoutain/wutaishan.db";
    static private SQLiteDatabase database;
    static private Context mContext;
    public static void setdatabase(){
        LitePal.getDatabase();
    }
    public static void setdata(Context context){
        File file = new File(dbPath);
        if(file.exists()){
            Log.e(TAG, "setdata: database目录下存在该文件");
            return ;
        }
        mContext = context;
        Log.e(TAG,"database目录下不存在该文件");
        database = SQLiteDatabase.openDatabase(srcPath,null,SQLiteDatabase.OPEN_READONLY);
        add_dadian_data();
        add_simiao_data();
        add_yinglian_data();
        addshiyi_data();
        add_chuanshuo_data();
        add_fojiaowehua_data();
        database.close();
    }

    public static void add_chuanshuo_data(){
        List<Chuanshuo> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from chuanshuo",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Chuanshuo chuanshuo = new Chuanshuo();
                String picName = cursor.getString(cursor.getColumnIndex("pic_name"));
                //chuanshuo.setChuanshuopic_id(cursor.getInt(cursor.getColumnIndex("chuanshuopic_id")));
                chuanshuo.setSimiao(cursor.getString(cursor.getColumnIndex("simiao")));
                chuanshuo.setName(cursor.getString(cursor.getColumnIndex("name")));
                chuanshuo.setContent(cursor.getString(cursor.getColumnIndex("content")));
                chuanshuo.setChuanshuopic_id(mContext.getResources().getIdentifier(picName,"drawable",mContext.getPackageName()));
                list.add(chuanshuo);
            }
            while (cursor.moveToNext());
        }
        LitePal.saveAll(list);
    }


    public static void add_fojiaowehua_data(){
        List<FoJiaowenhua> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from fojiaowenhua",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                FoJiaowenhua foJiaowenhua = new FoJiaowenhua();
                String buddhaPic = cursor.getString(cursor.getColumnIndex("fojiao_img_name"));
                foJiaowenhua.setName(cursor.getString(cursor.getColumnIndex("name")));
                //foJiaowenhua.setId_foujiao(cursor.getInt(cursor.getColumnIndex("id_foujiao")));
                foJiaowenhua.setContent(cursor.getString(cursor.getColumnIndex("content")));
                foJiaowenhua.setId_foujiao(mContext.getResources().getIdentifier(buddhaPic,"drawable",mContext.getPackageName()));
                list.add(foJiaowenhua);
            }
            while (cursor.moveToNext());
        }
        LitePal.saveAll(list);
    }

    public  static void add_yinglian_data(){
        List<Yinglian> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from yinglian",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                String imgName = cursor.getString(cursor.getColumnIndex("img_name"));
                Yinglian yinglian = new Yinglian();
                yinglian.setSize(cursor.getString(cursor.getColumnIndex("size")));
                yinglian.setDadian(cursor.getString(cursor.getColumnIndex("dadian")));
                yinglian.setFont(cursor.getString(cursor.getColumnIndex("font")));
                //yinglian.setImgid(cursor.getInt(cursor.getColumnIndex("imgid")));
                yinglian.setSimiao(cursor.getString(cursor.getColumnIndex("simiao")));
                yinglian.setWriter(cursor.getString(cursor.getColumnIndex("writer")));
                yinglian.setType(cursor.getString(cursor.getColumnIndex("type")));
                yinglian.setZhiyi(cursor.getString(cursor.getColumnIndex("zhiyi")));;

                if(imgName!=null && imgName.trim()!=""){
                    yinglian.setImgid(mContext.getResources().getIdentifier(imgName,"drawable",mContext.getPackageName()));
                    Log.e(TAG, "add_yinglian_data: "+imgName );
                }
                else{
                    yinglian.setImgid(mContext.getResources().getIdentifier("com_no_picture","drawable",mContext.getPackageName()));
                }

                list.add(yinglian);
            }
            while (cursor.moveToNext());
        }
        LitePal.saveAll(list);
    }
    public static void add_simiao_data(){
        List<SimiaoItem> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from simiaoitem",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                SimiaoItem simiaoItem = new SimiaoItem();
                String backPicName = cursor.getString(cursor.getColumnIndex("back_img_name"));
                String circlePicName = cursor.getString(cursor.getColumnIndex("circle_img_name"));
                String distributePicName = cursor.getString(cursor.getColumnIndex("fenbuimg_name"));
                String introPicName = cursor.getString(cursor.getColumnIndex("jianjie_img_name"));
                String titlePicName = cursor.getString(cursor.getColumnIndex("title_img_name"));

                simiaoItem.setLevel(cursor.getString(cursor.getColumnIndex("level")));
                simiaoItem.setName(cursor.getString(cursor.getColumnIndex("name")));
                simiaoItem.setNum_persion(cursor.getInt(cursor.getColumnIndex("num_persion")));
                simiaoItem.setJianjie(cursor.getString(cursor.getColumnIndex("jianjie")));

                simiaoItem.setJianjie_imgid(mContext.getResources().getIdentifier(introPicName,"drawable",mContext.getPackageName()));
                simiaoItem.setTitle_img(mContext.getResources().getIdentifier(titlePicName,"drawable",mContext.getPackageName()));
                simiaoItem.setCircle_imgid(mContext.getResources().getIdentifier(circlePicName,"drawable",mContext.getPackageName()));
                simiaoItem.setFenbuimgid(mContext.getResources().getIdentifier(distributePicName,"drawable",mContext.getPackageName()));
                simiaoItem.setBack_imgid(mContext.getResources().getIdentifier(backPicName,"drawable",mContext.getPackageName()));

                list.add(simiaoItem);
            }
            while (cursor.moveToNext());
        }
        LitePal.saveAll(list);
    }
    public static void add_dadian_data(){
        List<Dadian> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from dadian",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Dadian dadian = new Dadian();
                String titlePicName = cursor.getString(cursor.getColumnIndex("title_img_name"));
                String introPicName = cursor.getString(cursor.getColumnIndex("dadianjianjie_img_name"));
                String buddhaPicName = cursor.getString(cursor.getColumnIndex("fouxiang_img_name"));
                String buddhaDataPicName = cursor.getString(cursor.getColumnIndex("data_fouxiang_img_name"));

                if(titlePicName == null ||titlePicName.trim().length()==0){
                    titlePicName = "com_no_picture";
                }
                if(introPicName == null ||introPicName.trim().length()==0){
                    introPicName = "com_no_picture";
                }
                if(buddhaPicName == null ||buddhaPicName.trim().length()==0){
                    buddhaPicName = "com_no_picture";
                }
                if(buddhaDataPicName == null ||buddhaDataPicName.trim().length()==0){
                    buddhaDataPicName = "com_no_picture";
                }
                dadian.setSimiao(cursor.getString(cursor.getColumnIndex("simiao")));
                dadian.setName(cursor.getString(cursor.getColumnIndex("name")));
                dadian.setJianjie_dadian(cursor.getString(cursor.getColumnIndex("jianjie_dadian")));

                dadian.setData_fouxaing(mContext.getResources().getIdentifier(buddhaDataPicName,"drawable",mContext.getPackageName()));
                dadian.setTitle_imgid(mContext.getResources().getIdentifier(titlePicName,"drawable",mContext.getPackageName()));
                dadian.setDadianjianjie_imgid(mContext.getResources().getIdentifier(introPicName,"drawable",mContext.getPackageName()));
                dadian.setFouxiang_imgid(mContext.getResources().getIdentifier(buddhaPicName,"drawable",mContext.getPackageName()));
                list.add(dadian);
            }
            while (cursor.moveToNext());
        }
        LitePal.saveAll(list);
    }
    public static void addshiyi_data(){
        List<Shiyi> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from shiyi",null);
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                Shiyi shiyi = new Shiyi();
                shiyi.setSimiao(cursor.getString(cursor.getColumnIndex("simiao")));
                shiyi.setDadain(cursor.getString(cursor.getColumnIndex("dadain")));
                shiyi.setName(cursor.getString(cursor.getColumnIndex("name")));
                shiyi.setNum(cursor.getInt(cursor.getColumnIndex("num")));
                shiyi.setSrc(cursor.getString(cursor.getColumnIndex("src")));
                shiyi.setContent(cursor.getString(cursor.getColumnIndex("content")));
                list.add(shiyi);
            }
            while (cursor.moveToNext());
        }
        LitePal.saveAll(list);
    }
}
