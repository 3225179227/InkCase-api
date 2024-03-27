package com.more_sleep.inkcaseapi.mapper;

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
public class ArticleBodyMapperTest {
    @Autowired
    private IArticleBodyMapper articleBodyMapper;

    @Test
    public void getById() {
        System.out.println(articleBodyMapper.selectById(1));
    }
}
