package com.kaizen;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class KaizenApplication {

    public static void main(String[] args) {
        SpringApplication.run(KaizenApplication.class, args);
    }

}
