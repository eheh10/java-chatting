package com.client;

import com.protocol.Protocol;
import com.protocol.ProtocolFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ChatClient {
    private String serverFileName;
    private Socket socket;

    public ChatClient(String serverFileName, Socket socket) {
        this.serverFileName = serverFileName;
        this.socket = socket;
    }

    public void sendToClient(String msg) throws IOException {
        OutputStream os = socket.getOutputStream();

        os.write(msg.getBytes(StandardCharsets.UTF_8));
        os.flush();
//        os.close(); - 닫으면 안됨
    }

    public void writeSendMsg(String msg) throws IOException {
        OutputStream fos = new FileOutputStream(serverFileName,true);

        ProtocolFactory pf = new ProtocolFactory();
        Protocol protocol = pf.of(msg.substring(0,2));
        String fileMsg = protocol.getFileMsg(msg.substring(2));

        writeFile(fos,fileMsg);
    }

    public void receiveFromClient(String msg) throws IOException {
        writeFile(new FileOutputStream(serverFileName,true),msg);
    }

    private void writeFile(OutputStream os, String msg) throws IOException{
        os.write(msg.getBytes(StandardCharsets.UTF_8));

        os.flush();
        os.close();
    }
}
