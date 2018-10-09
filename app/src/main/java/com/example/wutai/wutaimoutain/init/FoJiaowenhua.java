package com.example.wutai.wutaimoutain.init;

import org.litepal.crud.DataSupport;

public class FoJiaowenhua extends DataSupport{
    private String name,content;
    private int id_foujiao;

    public int getId_foujiao() {
        return id_foujiao;
    }

    public void setId_foujiao(int id_foujiao) {
        this.id_foujiao = id_foujiao;
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
