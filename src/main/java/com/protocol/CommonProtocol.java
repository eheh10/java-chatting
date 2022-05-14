package com.protocol;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public abstract class CommonProtocol implements Protocol{
    @Override
    abstract public void action(String acceptTime, String msg) throws IOException;
    @Override
    abstract public boolean isSupport(String meta);

    @Override
    public void writeFile(String fileName, String msg) throws IOException {
        OutputStream fos = new BufferedOutputStream(new FileOutputStream(fileName,true),8192);

        fos.write(msg.getBytes(StandardCharsets.UTF_8));

        fos.flush();
        fos.close();
    }
}
