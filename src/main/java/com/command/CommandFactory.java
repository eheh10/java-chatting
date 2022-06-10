package com.command;

import java.util.Objects;

public class CommandFactory {

    public Command of(String cmd) {
        if (Objects.equals(cmd,"/send")){
            return new SendCommand();
        }else if (Objects.equals(cmd,"/notice")){
            return new NoticeCommand();
        }

        throw new RuntimeException("잘못된 명령어");
    }
}
