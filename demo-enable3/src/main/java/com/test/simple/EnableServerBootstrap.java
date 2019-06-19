package com.test.simple;


import com.test.simple.config.EnableServer;
import com.test.simple.config.Server;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableServer(type = Server.Type.HTTP)
public class EnableServerBootstrap {

    public static void main(String args[]){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(EnableServerBootstrap.class);
        context.refresh();
        Server server = context.getBean(Server.class);

        server.start();

        server.stop();

        context.close();
    }
}
