package com.example.wutai.wutaimoutain.jianjie_new;

public class Traver_simiao_pic {
    private int pic_id;
    private String simaio,dadian;

    public Traver_simiao_pic(int pic_id, String simaio, String dadian) {
        this.pic_id = pic_id;
        this.simaio = simaio;
        this.dadian = dadian;
    }

    public String getSimaio() {
        return simaio;
    }

    public String getDadian() {
        return dadian;
    }

    public int getPic_id() {
        return pic_id;
    }
}
