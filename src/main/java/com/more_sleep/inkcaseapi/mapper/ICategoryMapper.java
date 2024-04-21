package com.more_sleep.inkcaseapi.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.more_sleep.inkcaseapi.entity.Category;
import com.more_sleep.inkcaseapi.entity.vo.CategoryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 *
 */
@Mapper
public interface ICategoryMapper extends BaseMapper<Category> {
    @Select("select c.*, count(a.category_id) as articles from category c "
            + "left join article a on a.category_id = c.id group by c.id")
    List<CategoryVo> findAllDetail();

    @Select("select c.*, count(a.category_id) as articles from category c "
            + "left join article a on a.category_id = c.id where c.id = #{id}")
    CategoryVo getWithDetailById(@Param("id") Integer id);
}
