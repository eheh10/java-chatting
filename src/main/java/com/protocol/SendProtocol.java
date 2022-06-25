package com.protocol;

import com.util.SocketUtil;

import java.io.IOException;

public class SendProtocol extends CommonProtocol{
    @Override
    public void action(String acceptTime, String msg) throws IOException {
        StringBuilder sendMsg = new StringBuilder();

        sendMsg.append(SocketUtil.prefixServer()).append(msg);

        writeFile(acceptTime,sendMsg.toString());
    }

    @Override
    public String getFileMsg(String msg) {
        return new StringBuilder().append(SocketUtil.prefixServer())
                .append(msg.substring(2))
                .toString();
    }

//    @Override
//    public boolean isSupport(String meta) {
//        return Objects.equals(meta,"0:");
//    }
}
