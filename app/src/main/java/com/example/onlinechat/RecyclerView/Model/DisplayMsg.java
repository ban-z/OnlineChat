package com.example.onlinechat.RecyclerView.Model;

import java.security.PublicKey;

public class DisplayMsg {

    public static final int TYPE_RECEIVED = 0;
    public static final int TYPE_SENT = 1;
    private String content;
    private int type;

    public DisplayMsg(String content, int type){
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public int getType() {
        return type;
    }
}
