package com.example.onlinechat.RecyclerView.Model;

public class LinkmanPeople {

        private int account;
        private String rcmdOneself = "个人简介功能还未实现";

        public int getAccount() {
            return account;
        }

        public LinkmanPeople(int account){
            this.account = account;
            this.rcmdOneself = "个人简介功能还未实现";
        }

        public LinkmanPeople(int account, String rcmdOneself){
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
