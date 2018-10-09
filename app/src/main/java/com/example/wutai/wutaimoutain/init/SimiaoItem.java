package com.example.wutai.wutaimoutain.init;

import org.litepal.crud.DataSupport;

public class SimiaoItem extends DataSupport{
    private Integer back_imgid,num_persion,circle_imgid,jianjie_imgid,fenbuimgid,title_img;
    private String name,jianjie,level;

    public Integer getTitle_img() {
        return title_img;
    }

    public void setTitle_img(Integer title_img) {
        this.title_img = title_img;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getFenbuimgid() {
        return fenbuimgid;
    }

    public void setFenbuimgid(Integer fenbuimgid) {
        this.fenbuimgid = fenbuimgid;
    }

    public Integer getJianjie_imgid() {
        return jianjie_imgid;
    }

    public void setJianjie_imgid(Integer jianjie_imgid) {
        this.jianjie_imgid = jianjie_imgid;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public Integer getBack_imgid() {
        return back_imgid;
    }

    public void setBack_imgid(Integer back_imgid) {
        this.back_imgid = back_imgid;
    }

    public Integer getNum_persion() {
        return num_persion;
    }

    public void setNum_persion(Integer num_persion) {
        this.num_persion = num_persion;
    }

    public Integer getCircle_imgid() {
        return circle_imgid;
    }

    public void setCircle_imgid(Integer circle_imgid) {
        this.circle_imgid = circle_imgid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
