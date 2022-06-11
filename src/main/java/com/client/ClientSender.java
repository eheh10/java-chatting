package com.client;

import com.util.SocketUtil;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class ClientSender extends Thread {
    private final Writer fos;
    private final Writer osw;

    ClientSender(Socket socket,String acceptTime) throws IOException {
        osw = new OutputStreamWriter(
                    new BufferedOutputStream(socket.getOutputStream())
                    , StandardCharsets.UTF_8
            );
        fos = new OutputStreamWriter(
                new BufferedOutputStream(new FileOutputStream(acceptTime,true))
                ,StandardCharsets.UTF_8
        );
    }

    public void run() {
        InputStream is = System.in;
        BufferedInputStream bis = new BufferedInputStream(is,8192);
        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);

        int len;
        char[] buffer = new char[100];

        StringBuilder msg = new StringBuilder();
        String prefix = SocketUtil.prefixTime() + " [클라이언트] ";

        try {
            while((len=isr.read(buffer))!=-1){
                msg.append(buffer,0,len);

                if(msg.charAt(msg.length()-1)=='\n'){
                    String out = msg.toString();

                    osw.write(out);
                    fos.write(prefix +out);

                    fos.flush();
                    osw.flush();

                    msg.setLength(0);
                }
            }

            isr.close();
            osw.close();
            fos.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
