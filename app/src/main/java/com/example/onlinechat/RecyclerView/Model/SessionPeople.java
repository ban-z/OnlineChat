package com.example.onlinechat.RecyclerView.Model;

public class SessionPeople {

    private int account;
    private String rcmdOneself;

    public int getAccount() {
        return account;
    }

    public SessionPeople(int account){
        this.account = account;
        this.rcmdOneself = "动态消息显示还未实现";
    }

    public SessionPeople(int account, String rcmdOneself){
        this.account = account;
        this.rcmdOneself = rcmdOneself;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getRcmdOneself() {
        return rcmdOneself;
    }

    public void setRcmdOneself(String rcmdOneself) {
        this.rcmdOneself = rcmdOneself;
    }
}
