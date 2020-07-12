package com.example.onlinechat.Utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {

    private Activity activity = null;
    public SharedPreference(Activity activity){
        this.activity = activity;
    }

    public void setSharedPreferences(int account, String password){
        SharedPreferences.Editor editor = activity.getSharedPreferences("User", Context.MODE_PRIVATE).edit();
        editor.putInt("account", account);
        editor.putString("password", password);
        editor.apply();
    }

    public int getSharedUserAccount(){
        SharedPreferences preference = activity.getSharedPreferences("User", Context.MODE_PRIVATE);
        int account = preference.getInt("account", 000000);
        return account;
    }

    public String getSharedUserPassword(){
        SharedPreferences preference = activity.getSharedPreferences("User", Context.MODE_PRIVATE);
        String password = preference.getString("password", "000000");
        return password;
    }
}
