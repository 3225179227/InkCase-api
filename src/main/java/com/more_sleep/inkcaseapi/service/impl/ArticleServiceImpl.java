package com.more_sleep.inkcaseapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.more_sleep.inkcaseapi.entity.Article;
import com.more_sleep.inkcaseapi.entity.vo.DataVo;
import com.more_sleep.inkcaseapi.mapper.IArticleBodyMapper;
import com.more_sleep.inkcaseapi.mapper.IArticleMapper;
import com.more_sleep.inkcaseapi.mapper.ICategoryMapper;
import com.more_sleep.inkcaseapi.mapper.IUserMapper;
import com.more_sleep.inkcaseapi.service.IArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
@AllArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<IArticleMapper, Article> implements IArticleService {
    private final IArticleMapper articleMapper;
    private final IArticleBodyMapper articleBodyMapper;
    private final ICategoryMapper categoryMapper;
    private final IUserMapper userMapper;
    @Override
    public Page<Article> pageList(Page<Article> pageInfo, LambdaQueryWrapper<Article> queryWrapper) {

        // 把ArticleBody和Category装进去
        articleMapper.selectPage(pageInfo, queryWrapper);
        List<Article> list = pageInfo.getRecords().stream().peek(article -> {
            // 数据库中的body字段是ArticleBody的id
            // 应该如何处理？ 1. 通过id查询ArticleBody 2. 通过ArticleBodyMapper查询
            article.setAuthor(userMapper.selectById(article.getAuthorId()));
            article.setBody(articleBodyMapper.selectById(article.getBodyId()));
            article.setCategory(categoryMapper.selectById(article.getCategoryId()));
        }).toList();

        pageInfo.setRecords(list);
        return pageInfo;
    }

    @Override
    public Article getByIdWithBody(Long id) {
        Article article = articleMapper.selectById(id);
        article.setAuthor(userMapper.selectById(article.getAuthorId()));
        article.setBody(articleBodyMapper.selectById(article.getBodyId()));
        article.setCategory(categoryMapper.selectById(article.getCategoryId()));
        return article;
    }

    @Override
    public List<Article> listWithAll() {
        return list().stream().peek(article -> {
            article.setAuthor(userMapper.selectById(article.getAuthorId()));
            article.setBody(articleBodyMapper.selectById(article.getBodyId()));
            article.setCategory(categoryMapper.selectById(article.getCategoryId()));
        }).toList();
    }

    @Override
    public List<Article> getHot(Integer limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderBy(true, false, Article::getViewCounts);

        Page<Article> pageInfo = new Page<>();
        pageInfo.setSize(limit);
        pageInfo.setCurrent(0);
        return page(pageInfo,queryWrapper).getRecords();
    }

    @Override
    public List<Article> getNew(Integer limit) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderBy(true, false, Article::getCreateDate);

        Page<Article> pageInfo = new Page<>();
        pageInfo.setSize(limit);
        pageInfo.setCurrent(0);
        return page(pageInfo,queryWrapper).getRecords();
    }

    @Override
    public List<DataVo> getWithTime() {
        return articleMapper.getWithTime();
    }

    @Override
    public void updateByIdWithAll(Article article) {
        articleMapper.updateById(article);
        articleBodyMapper.updateById(article.getBody());
        categoryMapper.updateById(article.getCategory());
    }

    @Override
    public List<Article> getByCategoryId(Long id) {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Article::getCategoryId, id);
        return list(queryWrapper).stream().peek(article -> {
            article.setAuthor(userMapper.selectById(article.getAuthorId()));
            article.setBody(articleBodyMapper.selectById(article.getBodyId()));
            article.setCategory(categoryMapper.selectById(article.getCategoryId()));
        }).toList();
    }

    @Override
    public Article getArticleAndAddViews(Integer id) {
        Article article = articleMapper.selectById(id);
        article.setViewCounts(article.getViewCounts() + 1);
        articleMapper.updateById(article);
        article.setAuthor(userMapper.selectById(article.getAuthorId()));
        article.setBody(articleBodyMapper.selectById(article.getBodyId()));
        article.setCategory(categoryMapper.selectById(article.getCategoryId()));
        return article;
    }
}
