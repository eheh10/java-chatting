package com.protocol;

import com.util.SocketUtil;

import java.io.IOException;
import java.util.Objects;

public class NoticeProtocol extends CommonProtocol{
    @Override
    public void action(String acceptTime, String msg) throws IOException {
        StringBuilder writeMsg = new StringBuilder();

        writeMsg.append(SocketUtil.prefixTime()).append(" ").append(msg);
        writeMsg.setLength(writeMsg.length()-1); // \n 문자 제거

        System.out.print(writeMsg.toString());

        writeFile(acceptTime, getFileMsg(msg));
    }

    private String getType(String msg) {
        if (Objects.equals(msg.split(" ")[0],"\u001B[33m[INFO]\u001B[0m")) {
            return "[INFO]";
        }
        return "[WARN]";
    }

    @Override
    public String getFileMsg(String msg) {
        StringBuilder fileMsg = new StringBuilder();

        fileMsg.append(SocketUtil.prefixTime()).append(" ")
                .append(getType(msg)).append(" ");

        String[] values = msg.split(" ");
        for(int i=1; i<values.length; i++){
            fileMsg.append(values[i]).append(" ");
        }

        fileMsg.setLength(fileMsg.length()-2);

        return fileMsg.toString();
    }

//    @Override
//    public boolean isSupport(String meta) {
//        return Objects.equals(meta,"1:");
//    }
}
