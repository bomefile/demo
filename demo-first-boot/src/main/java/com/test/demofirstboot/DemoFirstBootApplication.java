package com.test.demofirstboot;

import com.test.demofirstboot.config.DemoAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

public class DemoFirstBootApplication {

	public static void main(String[] args) {

		SpringApplication.run(DemoAutoConfiguration.class, args);
	}



}
