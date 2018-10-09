package com.example.wutai.wutaimoutain.jianjie_new;

public class Traver_pic {
    private int pic_id;
    private String simiao,data;

    public Traver_pic(int pic_id, String simiao) {
        this.pic_id = pic_id;
        this.simiao = simiao;
    }

    public int getPic_id() {

        return pic_id;
    }

    public String getSimiao() {
        return simiao;
    }

    public String getData() {
        return data;
    }
}
