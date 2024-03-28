package com.more_sleep.inkcaseapi.service;

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
public class CommentTest {
    @Autowired
    private ICommentService commentService;

    @Test
    public void List() {
        commentService.list().forEach(System.out::println);
    }

    @Test
    public void getCommentById() {
        System.out.println(commentService.getByArticleId(1L));
    }
}
