package com.command;

import com.client.ChatClient;
import com.client.pool.Clients;

import java.io.IOException;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        for (String key : findClientKeys(ip)) {
            ChatClient chatClient = clients.get(key);
            chatClient.sendToClient(msg);
            chatClient.writeSendMsg(msg);
        }
    }

    private Set<String> findClientKeys(String ip) {

        if (Objects.isNull(ip)) {
            return Collections.emptySet();
        }

        if (Objects.equals(ip, "*") || Objects.equals(ip, "all")) {
            return clients.keySet();
        }

//        Set<String> findClientKeys = new HashSet<>();
//
//        for(String key : ip.split(",")){
//            if(clients.containsKey(key)){
//                findClientKeys.add(key);
//            }
//        }

        return Stream.of(ip.split(","))
                .filter(clients::containsKey)
                .collect(Collectors.toUnmodifiableSet());

//        return findClientKeys;
    }
}
