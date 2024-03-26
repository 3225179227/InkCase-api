package com.more_sleep.inkcaseapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.more_sleep.inkcaseapi.entity.Tag;
import com.more_sleep.inkcaseapi.entity.vo.TagVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 */
@Mapper
public interface ITagMapper extends BaseMapper<Tag> {
    @Select("select t.*,count(at.tag_id) as articles from me_article_tag at " +
            "right join me_tag t on t.id = at.tag_id " +
            "group by t.id order by count(at.tag_id) desc limit #{limit}")
    List<TagVo> listHotTagsByArticle(@Param("limit") int limit);

    @Select("select t.*,count(at.tag_id) as articles from me_article_tag at " +
            "right join me_tag t on t.id = at.tag_id " +
            "group by t.id order by count(at.tag_id) desc")
    List<TagVo> listAllTagsByArticle();
}
