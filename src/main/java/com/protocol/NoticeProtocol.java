package com.protocol;

import com.util.SocketUtil;

import java.io.IOException;

public class NoticeProtocol extends CommonProtocol{
    @Override
    public void action(String acceptTime, String msg) throws IOException {
        String prefix = SocketUtil.prefixTime() + " ";

        System.out.println(msg);

        msg = msg.replace("\u001B[33m","")
                .replace("\u001B[31m","")
                .replace("\u001B[0m","");
        writeFile(acceptTime, prefix + msg);
    }

//    @Override
//    public boolean isSupport(String meta) {
//        return Objects.equals(meta,"1:");
//    }
}
