package com.more_sleep.inkcaseapi.mapper;

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
public class ArticleMapperTest {
    @Autowired
    private IArticleMapper articleMapper;

    @Test
    public void List() {
        articleMapper.selectList(null).forEach(System.out::println);
    }
}
