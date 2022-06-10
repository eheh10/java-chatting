package com.protocol;

import com.util.SocketUtil;

import java.io.IOException;

public class SendProtocol extends CommonProtocol{
    @Override
    public void action(String acceptTime, String msg) throws IOException {
        String prefix = SocketUtil.prefixTime()+ " [서버] ";

        writeFile(acceptTime,prefix + msg);
    }

//    @Override
//    public boolean isSupport(String meta) {
//        return Objects.equals(meta,"0:");
//    }
}
