package com.more_sleep.inkcaseapi.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.more_sleep.inkcaseapi.common.R;
import com.more_sleep.inkcaseapi.entity.Article;
import com.more_sleep.inkcaseapi.entity.vo.DataVo;
import com.more_sleep.inkcaseapi.service.IArticleService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public R<Page<Article>> page(Integer pageNumber, Integer pageSize, Long categoryId) {

        System.out.println("lbj=="+categoryId);

        // 1.分页构造器
        Page<Article> pageInfo = new Page<>(pageNumber, pageSize);

        // 2.条件构造器
         LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
         queryWrapper.orderByDesc(Article::getCreateDate);

        if (categoryId != null){
            queryWrapper.eq(Article::getCategoryId, categoryId);
        }


        // 3.执行
        articleService.page(pageInfo, queryWrapper);
        return R.success(articleService.pageList(pageInfo, queryWrapper));
    }

    @GetMapping("/{id}")
    public R<Article> get(@PathVariable Long id) {
        return R.success(articleService.getByIdWithBody(id));
    }

    @GetMapping
    @Cacheable(value = "article", key = "(#year < T(java.time.LocalDate).now().getYear() || (#year == T(java.time.LocalDate).now().getYear() && #month <= T(java.time.LocalDate).now().getMonthValue())) ? #pageNumber+'_'+#pageSize+'_'+#year+'_'+#month+'_'+#categoryId : 'noCache'")
    public R<List<Article>> list(Integer pageNumber, Integer pageSize,
                                 Integer year, Integer month, Long categoryId) {
        return R.success(articleService.listWithAll(pageNumber, pageSize, year, month, categoryId));
    }

    @GetMapping("/hot")
    @Cacheable(value = "hot", key = "'hot'")
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
        System.out.println("lbj=="+id);
        return R.success(articleService.getByCategoryId(id));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = {"article"}, key = "'view_' + #id")
    public R<String> delete(@PathVariable Long id) {
        articleService.removeByIdWithAll(id);
        return R.success("删除成功");
    }

    @PutMapping
    @CacheEvict(value = {"article"}, key = "'view_' + #article.id")
    public R<String> update(@RequestBody Article article) {
        articleService.updateByIdWithAll(article);
        return R.success("修改成功");
    }

    @GetMapping("/view/{id}")
// 缓存文章的浏览量大于me.view.count

// 如果文章浏览量大于count，那么清除hot缓存
    @CacheEvict(value = "hot", allEntries = true, condition = "#result.data.viewCounts > @environment.getProperty('${me.view.count}', T(java.lang.Integer) )")
    public R<Article> getArticleAndAddViews(@PathVariable("id") Long id) {
        Article article = articleService.getArticleAndAddViews(id);

        return R.success(article);
    }

    @PostMapping("/publish")
    @CacheEvict(value = {"article"}, allEntries = true)
    public R<Map> publicArticle(@RequestBody Article article) {
        // 如果id为空，说明是新增
        Long articleId = null;
        if (article.getId() == null) {
            System.out.println("lbj==publishArticle");
            articleId = articleService.publishArticle(article);
        } else {
            System.out.println("lbj==up" + article);
            articleService.updateByIdWithAll(article);
            articleId = article.getId();
        }


        return R.success(Map.of("articleId", articleId));
    }


}
