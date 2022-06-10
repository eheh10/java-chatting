package com.protocol;

import java.util.Objects;

public class ProtocolFactory {

    public Protocol of(String meta) {
        if(Objects.equals(meta,"0:")){
            return new SendProtocol();
        }else if(Objects.equals(meta,"0:")){
            return new NoticeProtocol();
        }

        throw new RuntimeException("잘못된 명령어");
    }
}
