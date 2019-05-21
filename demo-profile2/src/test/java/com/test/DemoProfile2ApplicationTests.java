package com.test;

import com.test.service.MessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class DemoProfile2ApplicationTests {

    @Autowired
    private MessageService messageService;
    @Test
    public void contextLoads() {
        System.out.println(messageService.getMessage());
    }

}
