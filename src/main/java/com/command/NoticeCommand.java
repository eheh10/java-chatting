package com.command;

import java.io.IOException;
import java.util.Objects;

public class NoticeCommand extends CommonCommand{
    @Override
    public void action(String msg) throws IOException {
        msg = msg.replace("/notice","1:");

        String type = msg.split(" ")[1];
        String ip = msg.split(" ")[2];

        if (Objects.equals(type,"info")) {
            msg = msg.replace(" " + type + " " + ip + " ", "\u001B[33m[INFO]\u001B[0m ");
        }else if (Objects.equals(type,"warn")){
            msg = msg.replace(" " + type + " " + ip + " ", "\u001B[31m[WARN]\u001B[0m ");
        }

        sendToClients(ip,msg);
    }

    @Override
    public boolean isSupport(String meta) {
        return Objects.equals(meta,"/notice");
    }
}
