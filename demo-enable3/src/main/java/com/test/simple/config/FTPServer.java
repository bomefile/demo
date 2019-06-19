package com.test.simple.config;

import org.springframework.stereotype.Component;

@Component
public class FTPServer implements Server {
    @Override
    public void start() {
        System.out.println("FTP Server start");
    }

    @Override
    public void stop() {
        System.out.println("FTP Server stop");
    }

}
