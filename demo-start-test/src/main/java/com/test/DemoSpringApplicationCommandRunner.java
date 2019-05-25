package com.test;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoSpringApplicationCommandRunner implements CommandLineRunner, Ordered {
    @Override
    public void run(String... args) throws Exception {
        log.info("{} run ", this.getClass());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
