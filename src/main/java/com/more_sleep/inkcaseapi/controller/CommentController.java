package com.more_sleep.inkcaseapi.controller;

import com.more_sleep.inkcaseapi.common.R;
import com.more_sleep.inkcaseapi.entity.Article;
import com.more_sleep.inkcaseapi.entity.Comment;
import com.more_sleep.inkcaseapi.entity.User;
import com.more_sleep.inkcaseapi.service.IArticleService;
import com.more_sleep.inkcaseapi.service.ICommentService;
import com.more_sleep.inkcaseapi.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
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
    private final IUserService userService;

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
    @Transactional
    public R<Comment> create(@RequestBody Comment comment) {
        System.out.println("lbj!!: "+comment);
        comment.setArticleId(comment.getArticle().getId());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getByName(username);
        comment.setAuthorId(user.getId());
        comment.setLevel("0");
        comment.setAuthor(user);
        if (comment.getParent() != null) {
            comment.setParentId(comment.getParent().getId());
        }

        if (comment.getParent() != null) {
            comment.setLevel("1");
        }
        if (comment.getToUser() != null) {
            comment.setLevel("2");
        }
        commentService.save(comment);
        Long articleId = comment.getArticleId();
        // 根据articleId查询Article
        Article article = articleService.getById(articleId);
        article.setId(articleId);
        System.out.println("lbj!=: "+article.getCommentCounts());
        article.setCommentCounts(article.getCommentCounts() + 1);
        articleService.updateById(article);
        return R.success(comment);
    }
    @PostMapping("/create/change")
    public R<Comment> createChange(@RequestBody Comment comment) {
        Comment savedComment = commentService.saveCommentAndChangeCounts(comment);
        return R.success(savedComment);
    }
}
