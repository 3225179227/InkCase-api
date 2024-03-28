package com.more_sleep.inkcaseapi.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.more_sleep.inkcaseapi.entity.Article;
import com.more_sleep.inkcaseapi.entity.vo.DataVo;

import java.util.List;

/**
 *
 */
public interface IArticleService extends IService<Article> {
    Page<Article> pageList(Page<Article> pageInfo, LambdaQueryWrapper<Article> queryWrapper);

    Article getByIdWithBody(Long id);

    List<Article> listWithAll();

    List<Article> getHot(Integer limit);

    List<Article> getNew(Integer limit);

    List<DataVo> getWithTime();

    void updateByIdWithAll(Article article);

    List<Article> getByCategoryId(Long id);

    Article getArticleAndAddViews(Integer id);
}
