package com.test.simple.config;

public interface Server {

    void start();

    void stop();

    enum Type {
        HTTP,
        FTp
    }
}
