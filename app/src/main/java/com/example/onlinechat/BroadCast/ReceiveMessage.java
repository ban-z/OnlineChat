package com.example.onlinechat.BroadCast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ReceiveMessage extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String ingformation = intent.getStringExtra("informaiton");
        Toast.makeText(context, ingformation, Toast.LENGTH_SHORT).show();
    }
}
