package com.test.simple;


import com.test.simple.config.EnableHelloWorld;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHelloWorld
public class EnableHelloWorldBootstrap {

    public static void main(String args[]){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnableHelloWorldBootstrap.class);
        context.refresh();
        String helloWorld = context.getBean("helloworld",String.class);
        System.out.println("helloWorld = " + helloWorld);

        context.close();
    }
}
