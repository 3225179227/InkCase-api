package com.more_sleep.inkcaseapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.more_sleep.inkcaseapi.entity.Category;
import com.more_sleep.inkcaseapi.entity.vo.CategoryVo;

import java.util.List;

public interface ICategoryService extends IService<Category> {
    List<CategoryVo> findAllDetail();

    CategoryVo getWithDetailById(Integer id);
}
