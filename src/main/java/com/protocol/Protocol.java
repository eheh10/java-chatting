package com.protocol;

import java.io.IOException;

public interface Protocol {
    void action(String acceptTime, String msg) throws IOException;
//    boolean isSupport(String meta);
    void writeFile(String fileName, String msg) throws IOException;
}
