package com.example.onlinechat.Utils;

import java.io.IOException;
import java.io.ObjectOutputStream;
import com.example.onlinechat.Control.*;
import com.example.onlinechat.Data.Message;
import com.example.onlinechat.Data.MessageType;
import com.example.onlinechat.Data.Mine;

public class RequestFriends extends Thread {

    @Override
    public void run() {
        try {
            ObjectOutputStream OOS = new ObjectOutputStream(
                    ManageClientToServer.getClientOnlineThread(Mine.getAccount()).getclient().getOutputStream());
            Message message = new Message();
            message.setType(MessageType.RET_ALL_FRIENDS);
            message.setSender(Mine.getAccount());
            OOS.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
