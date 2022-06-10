package com.command;

import java.io.IOException;

public class SendCommand extends CommonCommand{
    @Override
    public void action(String msg) throws IOException {
        msg = msg.replace("/send","0:");

        String ip = msg.split(" ")[1];
        msg = msg.replace(" "+ip+" ","");

        sendToClients(ip,msg);
    }

//    @Override
//    public boolean isSupport(String meta) {
//        return Objects.equals(meta,"/send");
//    }
}
