package com.more_sleep.inkcaseapi.mapper;

import com.more_sleep.inkcaseapi.entity.vo.TagVo;
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
public class TagMapperTest {

    @Autowired
    private ITagMapper tagMapper;

    @Test
    public void List() {
        TagVo tagVo = new TagVo();
        tagVo.setAvatar("//www.baidu.com");
        tagVo.setTagName("test");
        tagVo.setArticles(1);
        System.out.println(tagVo);
        tagMapper.listAllTagsByArticle().forEach(System.out::println);
    }
    @Test
    public void ListHot() {
        TagVo tagVo = new TagVo();
        System.out.println(tagMapper.listHotTagsByArticle(6));
    }
}
