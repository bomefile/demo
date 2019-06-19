package com.test;

import com.test.listener.DemoApplicationContextInitializer;
import com.test.listener.DemoApplicationListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoStartTestApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication();

        DemoApplicationListener demoApplicationListener = new DemoApplicationListener();
        springApplication.addListeners(demoApplicationListener);

        DemoApplicationContextInitializer demoApplicationContextInitializer = new DemoApplicationContextInitializer();
        springApplication.addInitializers(demoApplicationContextInitializer);

        springApplication.run(DemoStartTestApplication.class, args);
    }

}
