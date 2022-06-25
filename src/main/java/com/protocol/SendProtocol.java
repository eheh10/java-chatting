package com.protocol;

import com.util.SocketUtil;

import java.io.IOException;

public class SendProtocol extends CommonProtocol{
    @Override
    public void action(String acceptTime, String msg) throws IOException {
        writeFile(acceptTime,getFileMsg(msg));
    }

    @Override
    public String getFileMsg(String msg) {
        return new StringBuilder().append(SocketUtil.prefixServer())
                .append(msg)
                .toString();
    }

//    @Override
//    public boolean isSupport(String meta) {
//        return Objects.equals(meta,"0:");
//    }
}
