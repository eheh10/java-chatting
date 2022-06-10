package com.command;

import com.client.ChatClient;
import com.server.ServerProgram;

import java.io.IOException;
import java.util.Objects;

public abstract class CommonCommand implements Command {
    @Override
    abstract public void action(String msg) throws IOException;
//    @Override
//    abstract public boolean isSupport(String meta);

    protected void sendToClients(String ip, String msg) throws IOException {
        if (Objects.equals(ip,"*") || Objects.equals(ip,"all")){
            for(String key : ServerProgram.clients.keySet() ){
                ChatClient chatClient = ServerProgram.clients.get(key);
                chatClient.sendMsg(msg);
            }
        }else{
            for(String key : ip.split(",")){
                if(ServerProgram.clients.containsKey(key)){
                    ChatClient chatClient = ServerProgram.clients.get(key);
                    chatClient.sendMsg(msg);
                }
            }
        }
    }
}
