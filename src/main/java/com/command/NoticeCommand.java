package com.command;

import com.client.pool.Clients;

import java.io.IOException;
import java.util.Objects;

public class NoticeCommand extends CommonCommand{
    public NoticeCommand(Clients clients) {
        super(clients);
    }

    @Override
    public void action(String msg) throws IOException {
        String[] values = msg.split(" ");
        String type = values[1];
        String ip = values[2];

        StringBuilder sendMsg = new StringBuilder();
        sendMsg.append("1:");

        if (Objects.equals(type,"info")) {
            sendMsg.append("\u001B[33m[INFO]\u001B[0m");
        }else if (Objects.equals(type,"warn")){
            sendMsg.append("\u001B[31m[WARN]\u001B[0m");
        }

        sendMsg.append(" ");

        for(int i=3; i<values.length; i++){
            sendMsg.append(values[i]).append(" ");
        }
        sendMsg.setLength(sendMsg.length()-1); //마지막 공백 문자 제거
        sendMsg.append("\n");

        sendToClients(ip,sendMsg.toString());
    }

//    @Override
//    public boolean isSupport(String meta) {
//        return Objects.equals(meta,"/notice");
//    }
}
