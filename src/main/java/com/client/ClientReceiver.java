package com.client;

import com.protocol.Protocol;
import com.protocol.ProtocolFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

class ClientReceiver extends Thread {
    private final String acceptTime;
    private final Reader isr;

    ClientReceiver(Socket socket, String acceptTime) throws IOException {
        this.acceptTime = acceptTime;
        isr = new InputStreamReader(
                new BufferedInputStream(socket.getInputStream())
                , StandardCharsets.UTF_8
        );
    }

    public void run() {
        int len;
        char[] buffer = new char[100];
        StringBuilder input = new StringBuilder();
        ProtocolFactory pf = new ProtocolFactory();

        try {
            while((len=isr.read(buffer))!=-1) {
                input.append(buffer,0,len);

                if(input.charAt(input.length()-1)=='\n'){
                    String in = input.toString();
                    String meta = in.substring(0,2);
                    String msg = in.substring(2,input.length());

//                    Protocol protocol = Stream.of(new SendProtocol(),new NoticeProtocol())
//                            .filter(p->p.isSupport(meta))
//                            .findFirst()
//                            .orElseThrow();
//
//                    protocol.action(acceptTime,msg);

                    Protocol protocol = pf.of(meta);
                    protocol.action(acceptTime,msg);

                    input.setLength(0);
                }
            }
            isr.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
