package com.test.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

@Slf4j
public class DemoSpringApplicationRunListener implements SpringApplicationRunListener, Ordered {


    private final SpringApplication application;

    private final String[] args;

    private final SimpleApplicationEventMulticaster initialMulticaster;


    public DemoSpringApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
        this.initialMulticaster = new SimpleApplicationEventMulticaster();
        for (ApplicationListener<?> listener : application.getListeners()) {
            this.initialMulticaster.addApplicationListener(listener);
        }
    }


    @Override
    public void starting() {
        log.info("{} start",this.getClass());
        this.initialMulticaster.multicastEvent(
                new ApplicationStartingEvent(this.application, this.args));
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        log.info("{} environmentPrepared",this.getClass());
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("{} contextPrepared",this.getClass());
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("{} contextLoaded",this.getClass());
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        log.info("{} started",this.getClass());
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        log.info("{} running",this.getClass());
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("{} failed",this.getClass());
    }

    @Override
    public int getOrder() {
        return 1;
    }
}
