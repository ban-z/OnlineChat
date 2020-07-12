package com.example.onlinechat.Control;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.onlinechat.Data.Message;
import com.example.onlinechat.Data.MessageType;
import com.example.onlinechat.Utils.FriendList;
import com.example.onlinechat.fragment.HomeFrag.ChatFragment;
import com.example.onlinechat.fragment.HomeFrag.LiveFragment;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import static com.example.onlinechat.fragment.HomeFrag.ChatFragment.msgList;

public class ClientOnlineThread extends Thread {

    private Context context;
    private Socket client;

    public Socket getclient(){
        return client;
    }

    public ClientOnlineThread(Context context, Socket client){
        this.context = context;
        this.client = client;
    }

    @Override
    public void run() {
        /*ObjectInputStream OIS = null;
        try {
            OIS = new ObjectInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        while (true){
            Message message;
            try {
                ObjectInputStream OIS = null;
                OIS = new ObjectInputStream(client.getInputStream());
                message = (Message) OIS.readObject();
                Log.d("ClientOnlineThread: ", "run: message +++++++++++++++++____________++++++++++++++++= " + message.getType() + ", " + message.getSender() + ", " + message.getReceiver() + ", " + message.getContent());

                if (message.getType().equals(MessageType.COM_MSG)){
                    String information = new String(message.getSender() +","+ message.getContent() +","+ message.getType());
                    Log.d("ClientOnlineThread: ", "run: information is " + information.toString());
                    /*//聊天消息，把从服务端获取的消息通过广播推送出去
                    //广播未发送成功
                    Intent intent = new Intent("com.example.onlinechat.MY_MSG_BROADCAST");
                    intent.putExtra("information", information);
                    context.sendBroadcast(intent);*/

                    //hander
                    android.os.Message msg = ChatFragment.getHandler().obtainMessage();
                    msg.what = 1;
                    msg.obj = information;
                    ChatFragment.getHandler().sendMessage(msg);

                }else if (message.getType().equals(MessageType.RET_ALL_FRIENDS)){
                    //好友列表
                    String friends[] = message.getContent().split(",");
                    Log.d("ClientOnlineThread: ", "run: Friends is " + friends.toString());
                    FriendList.setFriends(friends);
                }else if (message.getType().equals(MessageType.MAKE_BROADCAST)){
                    String broadcast = message.getContent();
                    Log.d("ClientOnlineThread: ", "run: Make BroadCast Content: " + broadcast);
                    //hander
                    android.os.Message msg = LiveFragment.getHandler().obtainMessage();
                    msg.what = 999;
                    msg.obj = broadcast;
                    LiveFragment.getHandler().sendMessage(msg);
                }
                if (message.getType().equals(MessageType.SUCCESS)){
                    Toast.makeText(context, "操作成功", Toast.LENGTH_SHORT).show();
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
