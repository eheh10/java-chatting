package com.util;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SocketUtil {
    public static String getIp(InetAddress inetAddress){
        return inetAddress.getHostAddress();
    }

    public static String nowTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
    }

    public static String prefixTime(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public static String prefixClient(){
        StringBuilder str = new StringBuilder();
        return str.append(prefixTime()).append(" ").append("[클라이언트]").append(" ").toString();
    }

    public static String prefixServer(){
        StringBuilder str = new StringBuilder();
        return str.append(prefixTime()).append(" ").append("[서버]").append(" ").toString();
    }
}
