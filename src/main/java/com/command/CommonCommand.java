package com.command;

import com.client.ChatClient;
import com.client.pool.Clients;

import java.io.IOException;
import java.util.Objects;

public abstract class CommonCommand implements Command {
    private Clients clients;

    public CommonCommand(Clients clients) {
        this.clients = clients;
    }

    @Override
    abstract public void action(String msg) throws IOException;
//    @Override
//    abstract public boolean isSupport(String meta);

    protected void sendToClients(String ip, String msg) throws IOException {
        if (Objects.equals(ip,"*") || Objects.equals(ip,"all")){
            for(String key : clients.keySet() ){
                ChatClient chatClient = clients.get(key);
                chatClient.sendMsg(msg);
            }
        }else{
            for(String key : ip.split(",")){
                if(clients.containsKey(key)){
                    ChatClient chatClient = clients.get(key);
                    chatClient.sendMsg(msg);
                }
            }
        }
    }
}
