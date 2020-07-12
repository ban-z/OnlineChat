package com.example.onlinechat.Control;

import java.util.HashMap;

public class ManageClientToServer {

    private static HashMap hashMap = new HashMap<Integer, ClientOnlineThread>();

    //将创建好的ClientOnlineThread放入到hashMap中
    public static void addClientTOServerThread(int account, ClientOnlineThread clientOnlineThread){
        hashMap.put(account, clientOnlineThread);
    }

    public static ClientOnlineThread getClientOnlineThread(int account){
        return (ClientOnlineThread) hashMap.get(account);
    }
}
