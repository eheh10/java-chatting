package com.command;

import java.io.IOException;

public interface Command {
    void action(String msg) throws IOException;
    boolean isSupport(String meta);
}
