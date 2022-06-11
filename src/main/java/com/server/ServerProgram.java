package com.server;

import com.client.ChatClient;
import com.client.Clients;
import com.util.SocketUtil;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class ServerProgram {
    public static Clients clients = new Clients(new HashMap<>());

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(7777);

        while(true){
            Socket socket = serverSocket.accept();
            String ip = SocketUtil.getIp(socket.getInetAddress());

            String serverFileName = SocketUtil.nowTime() + "_" + ip;

            if (!clients.containsKey(ip)) {
                clients.put(ip, new ChatClient(serverFileName,socket));
            }

            System.out.println(ip+":"+socket.getPort()+"연결");

            Thread sender = new Thread(new ServerSender());
            Thread receiver = new Thread(new ServerReceiver(socket));

            sender.start();
            receiver.start();
        }
    }
}

