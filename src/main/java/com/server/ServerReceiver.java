package com.server;

import com.client.ChatClient;
import com.client.pool.Clients;
import com.command.NoticeCommand;
import com.util.SocketUtil;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

class ServerReceiver extends Thread {
    private final Socket socket;
    private final Clients clients;
    private final Reader isr;
    private final NoticeCommand noticeCommand;

    ServerReceiver(Socket socket, Clients clients) throws IOException {
        this.socket = socket;
        this.clients = clients;
        this.noticeCommand = new NoticeCommand(clients);
        isr = new InputStreamReader(
                new BufferedInputStream(socket.getInputStream())
                , StandardCharsets.UTF_8
        );
    }

    public void run() {
        String ip = SocketUtil.getIp(socket.getInetAddress());
        int len;
        char[] buffer = new char[7];
        final int limit = 10;

        StringBuilder input = new StringBuilder();

        try {
            while((len=isr.read(buffer))!=-1){
                input.append(buffer,0,len);

                if (buffer[len-1]!='\n') {
                    continue;
                }

                if (input.length() > limit+1) {
                    noticeCommand.action("/notice warn "+ip+" 메세지 길이 제한 "+limit+"자를 넘겼습니다.\n");
                    input.setLength(0);
                    continue;
                }

                String in = input.toString();

                ChatClient chatClient = clients.get(ip);
                chatClient.receiveFromClient(processReceive(in));

                if (Objects.equals(in.strip(),"/exit")){
                    noticeCommand.action("/notice info "+ip+" 서버와의 연결이 종료되었습니다.\n");

                    if (clients.containsKey(ip)){
                        clients.remove(ip);
                    }

                    socket.close();
                    break;
                }

                input.setLength(0);
            }
            isr.close();

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private String processReceive(String in) {
        StringBuilder str = new StringBuilder();

        return str.append(SocketUtil.prefixClient()).append(in).toString();
    }
}
