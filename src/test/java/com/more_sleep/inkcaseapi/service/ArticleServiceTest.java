package com.more_sleep.inkcaseapi.service;

import com.more_sleep.inkcaseapi.mapper.IArticleMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ArticleServiceTest {

    @Autowired
    private IArticleService articleService;

    @Test
    public void List() {
        articleService.list().forEach(System.out::println);
    }
}
