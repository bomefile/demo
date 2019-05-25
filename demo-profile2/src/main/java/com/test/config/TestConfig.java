package com.test.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("test")
@PropertySource("classpath:env/test.properties")
@Configuration
public class TestConfig {

}
