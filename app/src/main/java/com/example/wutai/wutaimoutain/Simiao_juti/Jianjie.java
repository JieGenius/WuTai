package com.example.wutai.wutaimoutain.Simiao_juti;

public class Jianjie {
    private Integer bc_id,fxpic_id;
    private String name,content;
    private boolean issimiao;
    public Jianjie(Integer bc_id, Integer fxpic_id, String name, String content,boolean issimiao) {
        this.bc_id = bc_id;
        this.fxpic_id = fxpic_id;
        this.name = name;
        this.content = content;
        this.issimiao = issimiao;
    }

    public boolean isIssimiao() {
        return issimiao;
    }

    public Integer getBc_id() {
        return bc_id;
    }

    public Integer getFxpic_id() {
        return fxpic_id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }
}
