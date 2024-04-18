package com.example.springdemo;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringDemoApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("hello world");
    }

    @Value("${password.test1}")
    private String password1;
    @Value("${password.test2}")
    private String password2;

    @Test
    public void test() {
        log.info("password1: {}", password1);
        log.info("password2 {}", password2);
    }


}
