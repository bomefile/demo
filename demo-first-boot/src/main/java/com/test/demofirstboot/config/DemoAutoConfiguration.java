package com.test.demofirstboot.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class DemoAutoConfiguration {

    @RequestMapping("/")
    public String hello(){
        return "Hello,World!!!";
    }
}
