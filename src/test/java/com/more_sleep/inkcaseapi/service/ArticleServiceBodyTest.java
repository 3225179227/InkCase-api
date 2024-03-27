package com.more_sleep.inkcaseapi.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.more_sleep.inkcaseapi.entity.Article;
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
public class ArticleServiceBodyTest {

    @Autowired
    private IArticleBodyService articleBodyService;

    @Test
    public void List() {
        articleBodyService.list().forEach(System.out::println);
    }

    @Test
    public void getById() {
        System.out.println(articleBodyService.getById(1));
    }
}
