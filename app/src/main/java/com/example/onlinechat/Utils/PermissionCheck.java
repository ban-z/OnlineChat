package com.example.onlinechat.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionCheck {

    private Context context;
    public static int MY_PERMISSIONS_REQUEST_VIBRATE = 999;

    public PermissionCheck(Context context){
        this.context = context;
    }

    public void checkPermission(){
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.VIBRATE)
                           != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions((Activity) context, new String[]{
                    Manifest.permission.VIBRATE}, MY_PERMISSIONS_REQUEST_VIBRATE);
        }
    }
}
