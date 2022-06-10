package com.client;

import com.util.SocketUtil;

import java.io.IOException;
import java.net.Socket;

public class ClientProgram {
    public static void main(String[] args) throws IOException {
        String serverIp = "127.0.0.1";
        Socket socket = new Socket(serverIp,7777);
        String acceptTime = SocketUtil.nowTime();

        Thread sender = new Thread(new ClientSender(socket,acceptTime));
        Thread receiver = new Thread(new ClientReceiver(socket,acceptTime));
        sender.setDaemon(true);

        sender.start();
        receiver.start();
    }
}
