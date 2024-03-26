package com.more_sleep.inkcaseapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.more_sleep.inkcaseapi.entity.Article;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IArticleMapper extends BaseMapper<Article> {

}
