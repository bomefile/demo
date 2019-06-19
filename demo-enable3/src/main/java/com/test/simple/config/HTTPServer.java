package com.test.simple.config;

import org.springframework.stereotype.Component;


@Component
public class HTTPServer implements Server {
    @Override
    public void start() {
        System.out.println("HTTP Server start");
    }

    @Override
    public void stop() {
        System.out.println("HTTP Server stop");
    }

}
