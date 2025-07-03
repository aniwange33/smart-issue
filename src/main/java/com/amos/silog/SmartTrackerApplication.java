package com.amos.silog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j(topic = "main-app")
public class SmartTrackerApplication {


    public static void main(String[] args) {
        SpringApplication.run(SmartTrackerApplication.class, args);
    }


}
