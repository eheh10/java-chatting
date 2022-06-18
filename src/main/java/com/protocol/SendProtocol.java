package com.protocol;

import com.util.SocketUtil;

import java.io.IOException;

public class SendProtocol extends CommonProtocol{
    @Override
    public void action(String acceptTime, String msg) throws IOException {
        StringBuilder sendMsg = new StringBuilder();

        sendMsg.append(SocketUtil.prefixTime())
                .append(" ")
                .append("[서버]")
                .append(" ")
                .append(msg);

        writeFile(acceptTime,sendMsg.toString());
    }

//    @Override
//    public boolean isSupport(String meta) {
//        return Objects.equals(meta,"0:");
//    }
}
