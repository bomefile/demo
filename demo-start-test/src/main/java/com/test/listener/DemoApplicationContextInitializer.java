package com.test.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DemoApplicationContextInitializer implements ApplicationContextInitializer, Ordered {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        log.info("{} initialize", this.getClass());
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE-1;
    }
}
