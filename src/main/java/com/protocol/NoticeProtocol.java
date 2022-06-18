package com.protocol;

import com.util.SocketUtil;

import java.io.IOException;

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
        if (msg.indexOf("INFO") != -1){
            writeMsg.append("[INFO]").append(" ");
        }else if(msg.indexOf("WARN") != -1){
            writeMsg.append("[WARN]").append(" ");
        }

        for(int i=1; i<values.length-1; i++){
            writeMsg.append(values[i]).append(" ");
        }
        writeMsg.setLength(writeMsg.length()-1); // 마지막 공백 제거
        writeMsg.append("\n");

        writeFile(acceptTime, writeMsg.toString());
    }

//    @Override
//    public boolean isSupport(String meta) {
//        return Objects.equals(meta,"1:");
//    }
}
