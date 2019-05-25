package com.test.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("dev")
@PropertySource("classpath:env/dev.properties")
@Configuration
public class DevConfig {

}
