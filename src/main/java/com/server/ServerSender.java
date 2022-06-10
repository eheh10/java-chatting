package com.server;

import com.command.Command;
import com.command.CommandFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

class ServerSender extends Thread {

    public void run() {
        InputStream is = System.in;
        BufferedInputStream bis = new BufferedInputStream(is,8192);
        InputStreamReader isr = new InputStreamReader(bis, StandardCharsets.UTF_8);

        int len;
        char[] buffer = new char[100];

        StringBuilder input = new StringBuilder();
        CommandFactory cf = new CommandFactory();
        try {
            while((len=isr.read(buffer))!=-1){
                input.append(buffer,0,len);

                if (input.charAt(input.length()-1)=='\n') {
                    String in = input.toString();
                    String cmd = in.split(" ")[0];

//                    Command command = Stream.of(new SendCommand(), new NoticeCommand())
//                            .filter(c->c.isSupport(cmd))
//                            .findFirst()
//                            .orElseThrow();

                    Command command = cf.of(cmd);
                    command.action(in);

                    input.setLength(0);
                }
            }
            isr.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
