package com.more_sleep.inkcaseapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.more_sleep.inkcaseapi.entity.Category;
import com.more_sleep.inkcaseapi.entity.vo.CategoryVo;
import com.more_sleep.inkcaseapi.mapper.ICategoryMapper;
import com.more_sleep.inkcaseapi.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl extends ServiceImpl<ICategoryMapper, Category> implements ICategoryService {
    private final ICategoryMapper categoryMapper;

    @Override
    public List<CategoryVo> findAllDetail() {
        return categoryMapper.findAllDetail();
    }

    @Override
    public CategoryVo getWithDetailById(Integer id) {
        return categoryMapper.getWithDetailById(id);
    }
}
