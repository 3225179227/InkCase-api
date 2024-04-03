package com.more_sleep.inkcaseapi.controller;

import com.more_sleep.inkcaseapi.common.R;
import com.more_sleep.inkcaseapi.entity.Article;
import com.more_sleep.inkcaseapi.entity.Comment;
import com.more_sleep.inkcaseapi.service.IArticleService;
import com.more_sleep.inkcaseapi.service.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/comment")
@AllArgsConstructor
public class CommentController {
    private final ICommentService commentService;
    private final IArticleService articleService;

    @GetMapping
    public R<List<Comment>> list() {
        return R.success(commentService.list());
    }

    @GetMapping("/{id}")
    public R<Comment> getById(@PathVariable Long id) {
        return R.success(commentService.getById(id));
    }


    @GetMapping("/article/{id}")
    public R<List<Comment>> getByArticleId(@PathVariable Long id) {
        return R.success(commentService.getByArticleId(id));
    }

    @PostMapping("/create")
    public R<Comment> create(@RequestBody Comment comment) {
        comment.setArticleId(comment.getArticle().getId());
        comment.setAuthorId(comment.getAuthor().getId());
        comment.setToUserId(comment.getToUser().getId());

        commentService.save(comment);
        Long articleId = comment.getArticleId();
        Article article = new Article();
        article.setId(articleId);
        article.setCommentCounts(article.getCommentCounts());
        articleService.updateById(article);
        return R.success(comment);
    }
    @PostMapping("/create/change")
    public R<Comment> createChange(@RequestBody Comment comment) {
        Comment savedComment = commentService.saveCommentAndChangeCounts(comment);
        return R.success(savedComment);
    }
}
