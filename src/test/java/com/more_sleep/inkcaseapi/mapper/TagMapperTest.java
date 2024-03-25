package com.more_sleep.inkcaseapi.mapper;

import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AllArgsConstructor
public class TagMapperTest {
    private ITagMapper tagMapper;

    @Test
    public void List() {
        tagMapper.selectList(null).forEach(System.out::println);
    }
}
