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
        String prefix = "";
        boolean IsNoticeCmd = Objects.equals(msg.charAt(0),'1');

        if(IsNoticeCmd){
            prefix = SocketUtil.prefixTime()+" ";
        }else{
            prefix = SocketUtil.prefixTime()+" [서버] ";
        }

        OutputStream os = socket.getOutputStream();
        OutputStream fos = new FileOutputStream(serverFileName,true);

        os.write(msg.getBytes(StandardCharsets.UTF_8));
        os.flush();
//        os.close(); - 닫으면 안됨

        msg = msg.split(":")[1]
                .replace("\u001B[33m","")
                .replace("\u001B[31m","")
                .replace("\u001B[0m","");

        fos.write((prefix+msg).getBytes(StandardCharsets.UTF_8));
        fos.flush();
        fos.close();
    }

    public void receiveMsg(String msg) throws IOException {
        String prefix = SocketUtil.prefixTime()+ " [클라이언트] ";

        OutputStream fos = new FileOutputStream(serverFileName,true);

        fos.write((prefix+msg).getBytes(StandardCharsets.UTF_8));

        fos.flush();
        fos.close();
    }
}
