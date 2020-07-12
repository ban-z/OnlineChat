package com.example.onlinechat.Utils;

import java.util.ArrayList;
import java.util.List;

public class FriendList {

    private static List<Integer> friends = new ArrayList<Integer>();

    static {
        if (friends == null){
            friends = new ArrayList<Integer>();
        }
    }

    public static List<Integer> getFriends() {
        return friends;
    }

    public static void setFriends(String[] friendStr) {
        for (int i = 0; i < friendStr.length; i++){
            friends.add(Integer.parseInt(friendStr[i]));
        }
    }
}
