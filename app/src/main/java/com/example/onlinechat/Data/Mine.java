package com.example.onlinechat.Data;

public class Mine {
    private static int account;
    private static String password;

    public static int getAccount() {
        return account;
    }

    public static void setAccount(int account) {
        Mine.account = account;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Mine.password = password;
    }
}
