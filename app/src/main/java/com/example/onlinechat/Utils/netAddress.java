package com.example.onlinechat.Utils;

public class netAddress {

    private static String SOCKET_IP = "192.168.43.93";
    private static int SOCKET_PORT = 6767;

    public static String getSocketIp() {
        return SOCKET_IP;
    }

    public static void setSocketIp(String socketIp) {
        SOCKET_IP = socketIp;
    }

    public static int getSocketPort() {
        return SOCKET_PORT;
    }

    public static void setSocketPort(int socketPort) {
        SOCKET_PORT = socketPort;
    }
}
