package com.command;

import com.client.pool.Clients;

import java.util.Objects;

public class CommandFactory {

    public Command of(String cmd, Clients clients) {
        if (Objects.equals(cmd,"/send")){
            return new SendCommand(clients);
        }else if (Objects.equals(cmd,"/notice")){
            return new NoticeCommand(clients);
        }

        throw new RuntimeException("잘못된 명령어");
    }
}
