package com.test.simple.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Import(HelloWorldConfiguration.class)
public @interface EnableHelloWorld {
}
