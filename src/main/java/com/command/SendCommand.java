package com.command;

import com.client.pool.Clients;

import java.io.IOException;

public class SendCommand extends CommonCommand{
    public SendCommand(Clients clients) {
        super(clients);
    }

    @Override
    public void action(String msg) throws IOException {
        String[] values = msg.split(" ");
        String ip = values[1];
        StringBuilder sendMsg = new StringBuilder();

        sendMsg.append("0:");

        for(int i=2; i<values.length; i++){
            sendMsg.append(values[i]).append(" ");
        }
        sendMsg.setLength(sendMsg.length()-1); //마지막 공백 문자 제거

        sendToClients(ip,sendMsg.toString());
    }

//    @Override
//    public boolean isSupport(String meta) {
//        return Objects.equals(meta,"/send");
//    }
}
