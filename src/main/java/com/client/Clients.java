package com.client;

import java.util.Map;
import java.util.Set;

public class Clients {
    private Map<String,ChatClient> clients;

    public Clients(Map<String,ChatClient> clients) {
        this.clients = clients;
    }


    public boolean containsKey(String ip) {
        return clients.containsKey(ip);
    }


    public void put(String ip, ChatClient chatClient) {
        clients.put(ip, chatClient);
    }

    public ChatClient get(String ip) {
        return clients.get(ip);
    }

    public void remove(String ip) {
        clients.remove(ip);
    }

    public Set<String> keySet() {
        return clients.keySet();
    }
}
