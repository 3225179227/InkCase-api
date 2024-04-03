package com.more_sleep.inkcaseapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class InkCaseApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(InkCaseApiApplication.class, args);
    }
}
