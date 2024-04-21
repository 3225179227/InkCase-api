package com.more_sleep.inkcaseapi.controller;

import com.more_sleep.inkcaseapi.common.R;
import com.more_sleep.inkcaseapi.entity.Category;
import com.more_sleep.inkcaseapi.entity.vo.CategoryVo;
import com.more_sleep.inkcaseapi.service.ICategoryService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/category")
@AllArgsConstructor
public class CategoryController {
    private final ICategoryService categoryService;

    @GetMapping
    public R<List<Category>> list() {
        return R.success(categoryService.list());
    }

    @GetMapping("/detail")
    @Cacheable(value = "category", key = "'detail'")
    public R<List<CategoryVo>> listCategoryDetail() {
        List<CategoryVo> category = categoryService.findAllDetail();
        return R.success(category);
    }

    @GetMapping("/{id}")
    public R<Category> getCategoryById(@PathVariable("id") Integer id) {


        if (null == id) {
            return R.error("id不能为空");
        }

        Category category = categoryService.getById(id);

        return R.success(category);
    }

    @GetMapping("/detail/{id}")
    public R<CategoryVo> getCategoryDetailById(@PathVariable("id") Integer id) {

        if (null == id) {
            return R.error("id不能为空");
        }

        CategoryVo categoryVo = categoryService.getWithDetailById(id);

        return R.success(categoryVo);
    }
}
