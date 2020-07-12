package com.example.onlinechat.Control;

import android.content.Context;

import com.example.onlinechat.Data.Message;
import com.example.onlinechat.Data.MessageType;
import com.example.onlinechat.Data.Mine;
import com.example.onlinechat.Data.User;
import com.example.onlinechat.MainActivity;
import com.example.onlinechat.Utils.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

    private Context context;
    public Socket client;

    public Client(Context context) {
        this.context = context;
    }

    public boolean sendLoginInfo(Object object) {
        boolean flag = false;
        client = new Socket();
        try {
            try {
                client.connect(new InetSocketAddress(netAddress.getSocketIp(), netAddress.getSocketPort()), 2000);
            } catch (IOException e) {
                return false;
            }

            ObjectOutputStream OOS = new ObjectOutputStream(client.getOutputStream());
            OOS.writeObject(object);
            OOS.flush();
            ObjectInputStream OIS = new ObjectInputStream(client.getInputStream());
            Message message = (Message) OIS.readObject();

            if (message.getType().equals(MessageType.SUCCESS)){
                //个人信息
                String[] mine = message.getContent().split(",");
                Mine.setAccount(Integer.parseInt(mine[0]));
                Mine.setPassword(mine[1]);
                //创建与服务器保连接的线程
                ClientOnlineThread clientOnlineThread = new ClientOnlineThread(context, client);
                //创建完成后及启动该线程通信
                clientOnlineThread.start();
                //加入到管理类
                ManageClientToServer.addClientTOServerThread(((User)object).getAcount(), clientOnlineThread);

                //此时切换flag为true
                flag = true;
            }else if (message.getType().equals(MessageType.FAIL)){
                flag = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean sendRegisterInfo(Object object){
        boolean flag = false;
        try {
            try {
                client.connect(new InetSocketAddress(netAddress.getSocketIp(), netAddress.getSocketPort()), 2000);
            } catch (IOException e) {
                return false;
            }
            ObjectOutputStream OOS = new ObjectOutputStream(client.getOutputStream());
            OOS.writeObject(object);
            ObjectInputStream OIS = new ObjectInputStream(client.getInputStream());
            Message message = (Message) OIS.readObject();

            if (message.getType().equals(MessageType.SUCCESS)){
                flag = true;
            }else if (message.getType().equals(MessageType.FAIL)){
                flag = false;
            }else {
                flag = false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return flag;
    }
}
