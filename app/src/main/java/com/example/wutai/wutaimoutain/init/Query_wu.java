package com.example.wutai.wutaimoutain.init;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;

public class Query_wu {
    public static List<Yinglian> query_yinglian0(){

        List<Yinglian> yinglians = LitePal.limit(15).find(Yinglian.class);
        return yinglians;
    }
    public static List<Yinglian> query_yinglian(String name){
        List<Yinglian> yinglians = LitePal.where("simiao = ?",name).find(Yinglian.class);
        return yinglians;
    }
    public static List<Yinglian> query_yinglian2(String dadian){
        List<Yinglian> yinglians1 = LitePal.where("dadian = ?",dadian).find(Yinglian.class);
        return yinglians1;
    }
    public static List<Yinglian> query_yinglian1(String name,String dadian){
        List<Yinglian> yinglians = LitePal.where("simiao = ? and dadian like ?",name,"%"+dadian+"%").find(Yinglian.class);
        return yinglians;
    }
    public static List<SimiaoItem> query_simiao(){
        List<SimiaoItem> simiaoItems = LitePal.findAll(SimiaoItem.class);
        return simiaoItems;
    }
    public static List<SimiaoItem> query_simiao1(String level){
        List<SimiaoItem> simiaoItems = LitePal.where("level = ?",level).find(SimiaoItem.class);
        return simiaoItems;
    }
    public static List<FoJiaowenhua> query_foujiaowenhua(){
        List<FoJiaowenhua> all = LitePal.findAll(FoJiaowenhua.class);
        return all;
    }
    public static List<FoJiaowenhua> query_foujiaowenhua(String name){
        List<FoJiaowenhua> all = LitePal.where("name = ?",name).find(FoJiaowenhua.class);
        return all;
    }
    public static List<Chuanshuo> query_chuanshuo(String simiao){
        List<Chuanshuo> chuanshuos = LitePal.where("simiao = ?",simiao).find(Chuanshuo.class);
        return chuanshuos;
    }
    public static List<Chuanshuo> query_chuanshuo1(String name){
        List<Chuanshuo> chuanshuos = LitePal.where("name = ?", name).find(Chuanshuo.class);
        return chuanshuos;
    }
    public static List<Chuanshuo> query_chuanshuo(){
        List<Chuanshuo> chuanshuos = LitePal.findAll(Chuanshuo.class);
        return chuanshuos;
    }
    public static List<SimiaoItem> query_simiao(String name){
        List<SimiaoItem> simiaoItems = LitePal.where("name = ?",name).find(SimiaoItem.class);
        return simiaoItems;
    }
    public static List<Dadian> query_dadian(String name){
        List<Dadian> dadians = LitePal.where("simiao = ?",name).find(Dadian.class);
        return dadians;
    }


    public static List<Dadian> query_dadian(String name,String dadian){
        List<Dadian> dadians = LitePal.where("simiao = ? and name = ?",name,dadian).find(Dadian.class);
        return dadians;
    }

    public static List<Shiyi>  query_shiyi(String simiao,String dadian){
        List<Shiyi> shiyis = LitePal.where("simiao = ? and dadain = ?",simiao,dadian).find(Shiyi.class);
        return shiyis;
    }
}
