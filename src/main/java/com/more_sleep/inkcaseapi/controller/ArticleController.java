package com.more_sleep.inkcaseapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.more_sleep.inkcaseapi.common.R;
import com.more_sleep.inkcaseapi.entity.Article;
import com.more_sleep.inkcaseapi.entity.vo.DataVo;
import com.more_sleep.inkcaseapi.service.IArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */


@RestController
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {
    private final IArticleService articleService;


    // http://localhost:8888/articles?pageNumber=1&pageSize=5&name=a.createDate&sort=desc
    @GetMapping("/page")
    public R<Page<Article>> page(Integer pageNumber, Integer pageSize) {
        // 1.分页构造器
        Page<Article> pageInfo = new Page<>(pageNumber, pageSize);

        // 2.条件构造器
         LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
         queryWrapper.orderByDesc(Article::getCreateDate);

        // 3.执行
        articleService.page(pageInfo, queryWrapper);
        return R.success(articleService.pageList(pageInfo, queryWrapper));
    }

    @GetMapping("/{id}")
    public R<Article> get(@PathVariable Long id) {
        return R.success(articleService.getByIdWithBody(id));
    }

    @GetMapping
    public R<List<Article>> list() {
        return R.success(articleService.listWithAll());
    }

    @GetMapping("/hot")
    public R<List<Article>> getHot() {
        return R.success(articleService.getHot(6));
    }

    @GetMapping("/new")
    public R<List<Article>> getNew() {
        return R.success(articleService.getNew(6));
    }

    @GetMapping("/listArchives")
    public R<List<DataVo>> getWithTime() {
        return R.success(articleService.getWithTime());
    }

    @GetMapping("/category/{id}")
    public R<List<Article>> getByCategoryId(@PathVariable Long id) {
        return R.success(articleService.getByCategoryId(id));
    }

    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id) {
        articleService.removeById(id);
        return R.success("删除成功");
    }

    @PutMapping
    public R<String> update(@RequestBody Article article) {
        articleService.updateByIdWithAll(article);
        return R.success("修改成功");
    }
}
