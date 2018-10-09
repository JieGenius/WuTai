package com.example.wutai.wutaimoutain.Share;

public class Share {
    private int userimagid,shareimagid;
    private String username,content;
    private final String time;
    public Share(int userimagid,int shareimagid,String username,String time,String content){
        this.shareimagid = shareimagid;
        this.time = time;
        this.username = username;
        this.userimagid = userimagid;
        this.content = content;
    }

    public int getShareimagid() {
        return shareimagid;
    }

    public int getUserimagid() {
        return userimagid;
    }

    public String getTime() {
        return time;
    }

    public String getUsername() {
        return username;
    }

    public String getContent() {
        return content;
    }
}
