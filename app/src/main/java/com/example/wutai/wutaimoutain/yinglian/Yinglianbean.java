package com.example.wutai.wutaimoutain.yinglian;

public class Yinglianbean {
    private String writer,size,font,simiao,dadian,type,zhiyi;
    private Integer imgid;

    public Yinglianbean(String writer, String size, String font, String simiao, String dadian, String type, String zhiyi, Integer imgid) {
        this.writer = writer;
        this.size = size;
        this.font = font;
        this.simiao = simiao;
        this.dadian = dadian;
        this.type = type;
        this.zhiyi = zhiyi;
        this.imgid = imgid;
    }

    public String getZhiyi() {
        return zhiyi;
    }

    public void setZhiyi(String zhiyi) {
        this.zhiyi = zhiyi;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSimiao() {
        return simiao;
    }

    public String getDadian() {
        return dadian;
    }

    public void setSimiao(String simiao) {
        this.simiao = simiao;
    }

    public void setDadian(String dadian) {
        this.dadian = dadian;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setFont(String font) {
        this.font = font;
    }


    public void setImgid(Integer imgid) {
        this.imgid = imgid;
    }

    public String getWriter() {
        return writer;
    }

    public String getSize() {
        return size;
    }

    public String getFont() {
        return font;
    }

    public Integer getImgid() {
        return imgid;
    }
}
