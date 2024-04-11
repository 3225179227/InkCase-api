package com.more_sleep.inkcaseapi;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@EnableKnife4j
public class InkCaseApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(InkCaseApiApplication.class, args);
    }
}
