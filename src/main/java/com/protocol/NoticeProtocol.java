package com.protocol;

import com.util.SocketUtil;

import java.io.IOException;
import java.util.Objects;

public class NoticeProtocol extends CommonProtocol{
    @Override
    public void action(String acceptTime, String msg) throws IOException {
        String[] values = msg.split(" ");
        String prefix = SocketUtil.prefixTime();
        StringBuilder writeMsg = new StringBuilder();

        writeMsg.append(prefix).append(" ").append(msg);
        writeMsg.setLength(writeMsg.length()-1); // \n 문자 제거

        System.out.print(writeMsg.toString());

        writeMsg.setLength(0);

        writeMsg.append(prefix).append(" ");
        writeMsg.append(getType(msg)).append(" ");

        for(int i=1; i<values.length; i++){
            writeMsg.append(values[i]).append(" ");
        }
        writeMsg.setLength(writeMsg.length()-2); // 마지막 공백, \n 제거

        writeFile(acceptTime, writeMsg.toString());
    }

    private String getType(String msg) {
        if (Objects.equals(msg.split(" ")[1],"\u001B[33m[INFO]\u001B[0m")) {
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
