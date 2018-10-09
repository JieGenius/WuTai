package com.example.wutai.wutaimoutain.init;

import org.litepal.crud.DataSupport;

public class Dadian extends DataSupport{
    private Integer dadianjianjie_imgid,fouxiang_imgid,title_imgid,data_fouxaing;
    private String jianjie_dadian,simiao,name;

    public Integer getData_fouxaing() {
        return data_fouxaing;
    }

    public void setData_fouxaing(Integer data_fouxaing) {
        this.data_fouxaing = data_fouxaing;
    }

    public Integer getTitle_imgid() {
        return title_imgid;
    }

    public void setTitle_imgid(Integer title_imgid) {
        this.title_imgid = title_imgid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSimiao() {
        return simiao;
    }

    public void setSimiao(String simiao) {
        this.simiao = simiao;
    }

    public Integer getDadianjianjie_imgid() {
        return dadianjianjie_imgid;
    }

    public void setDadianjianjie_imgid(Integer dadianjianjie_imgid) {
        this.dadianjianjie_imgid = dadianjianjie_imgid;
    }

    public Integer getFouxiang_imgid() {
        return fouxiang_imgid;
    }

    public void setFouxiang_imgid(Integer fouxiang_imgid) {
        this.fouxiang_imgid = fouxiang_imgid;
    }

    public String getJianjie_dadian() {
        return jianjie_dadian;
    }

    public void setJianjie_dadian(String jianjie_dadian) {
        this.jianjie_dadian = jianjie_dadian;
    }
}
