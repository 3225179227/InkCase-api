package com.more_sleep.inkcaseapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.more_sleep.inkcaseapi.entity.Article;
import com.more_sleep.inkcaseapi.entity.vo.DataVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IArticleMapper extends BaseMapper<Article> {

    @Select("select year(create_date) as year,month(create_date) as month,count(*) as count " +
            "from me_article group by year(create_date),month(create_date)")
    List<DataVo> getWithTime();
}
