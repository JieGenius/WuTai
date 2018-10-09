package com.example.wutai.wutaimoutain.init;

import org.litepal.crud.DataSupport;

public class Chuanshuo extends DataSupport {
    private String simiao,name,content;
    private int chuanshuopic_id;

    public int getChuanshuopic_id() {
        return chuanshuopic_id;
    }

    public void setChuanshuopic_id(int chuanshuopic_id) {
        this.chuanshuopic_id = chuanshuopic_id;
    }

    public String getSimiao() {
        return simiao;
    }

    public void setSimiao(String simiao) {
        this.simiao = simiao;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
