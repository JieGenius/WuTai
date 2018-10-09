package com.example.wutai.wutaimoutain.init;

import org.litepal.crud.DataSupport;

public class Shiyi extends DataSupport{
    private String simiao,dadain,name,content,src;
    int num;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSimiao() {
        return simiao;
    }

    public void setSimiao(String simiao) {
        this.simiao = simiao;
    }

    public String getDadain() {
        return dadain;
    }

    public void setDadain(String dadain) {
        this.dadain = dadain;
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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
