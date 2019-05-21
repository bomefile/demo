package com.test;

import com.test.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoProfile2Application implements CommandLineRunner {

    @Autowired
    private MessageService messageService;

    public static void main(String[] args) {
        SpringApplication.run(DemoProfile2Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(messageService.getMessage());
    }
}
