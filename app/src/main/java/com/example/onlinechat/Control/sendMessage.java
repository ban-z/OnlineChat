package com.example.onlinechat.Control;

import android.util.Log;

import com.example.onlinechat.Data.Message;
import com.example.onlinechat.Data.Mine;

import java.io.IOException;

import java.io.ObjectOutputStream;

public class sendMessage {

    public static void sendMsg(int RAccount, String content, String type){
        try {
            int myAccount = Mine.getAccount();
            //通过account找到该线程，获取OutputStream
            ObjectOutputStream OOS = new ObjectOutputStream(
                    ManageClientToServer.getClientOnlineThread(myAccount).getclient().getOutputStream());
            //发送消息
            Message message = new Message();
            message.setType(type);
            message.setSender(myAccount);
            message.setContent(content);
            message.setReceiver(RAccount);
            //发送
            OOS.writeObject(message);
            OOS.flush();
            Log.d("sendMessage",
                    "sendMsg:-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=->>> " + message.getContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
