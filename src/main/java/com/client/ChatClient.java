package com.client;

import com.util.SocketUtil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ChatClient {
    private String serverFileName;
    private Socket socket;

    public ChatClient(String serverFileName, Socket socket) {
        this.serverFileName = serverFileName;
        this.socket = socket;
    }

    public void sendMsg(String msg) throws IOException {
        OutputStream os = socket.getOutputStream();
        OutputStream fos = new FileOutputStream(serverFileName,true);

        os.write(msg.getBytes(StandardCharsets.UTF_8));
        os.flush();
//        os.close(); - 닫으면 안됨

        StringBuilder sendMsg = new StringBuilder();
        String[] values = msg.split(" ");

        if(Objects.equals(msg.charAt(0),'0')){
            sendMsg.append(SocketUtil.prefixServer()).append(msg.substring(2));
            return;
        }

        sendMsg.append(SocketUtil.prefixTime()).append(" ");

        if (msg.contains("INFO")) {
            sendMsg.append("[INFO]").append(" ");
        } else if (msg.contains("WARN")) {
            sendMsg.append("[WARN]").append(" ");
        }

        for (int i = 1; i < values.length; i++) {
            sendMsg.append(values[i]).append(" ");
        }

        sendMsg.setLength(sendMsg.length()-2);

        fos.write(sendMsg.toString().getBytes(StandardCharsets.UTF_8));
        fos.flush();
        fos.close();
    }

    public void receiveMsg(String msg) throws IOException {
        OutputStream fos = new FileOutputStream(serverFileName,true);

        fos.write(msg.getBytes(StandardCharsets.UTF_8));

        fos.flush();
        fos.close();
    }
}
