package com.more_sleep.inkcaseapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.more_sleep.inkcaseapi.entity.Article;
import com.more_sleep.inkcaseapi.entity.ArticleBody;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IArticleBodyMapper extends BaseMapper<ArticleBody> {
}