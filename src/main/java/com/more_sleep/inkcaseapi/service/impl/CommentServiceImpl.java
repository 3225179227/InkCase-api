package com.more_sleep.inkcaseapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.more_sleep.inkcaseapi.entity.Article;
import com.more_sleep.inkcaseapi.entity.Comment;
import com.more_sleep.inkcaseapi.entity.User;
import com.more_sleep.inkcaseapi.mapper.IArticleMapper;
import com.more_sleep.inkcaseapi.mapper.ICategoryMapper;
import com.more_sleep.inkcaseapi.mapper.ICommentMapper;
import com.more_sleep.inkcaseapi.mapper.IUserMapper;
import com.more_sleep.inkcaseapi.service.ICommentService;
import com.more_sleep.inkcaseapi.service.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
@AllArgsConstructor
public class CommentServiceImpl extends ServiceImpl<ICommentMapper, Comment> implements ICommentService{
    private final ICommentMapper commentMapper;
    private final ICategoryMapper categoryMapper;
    private final IArticleMapper articleMapper;
    private final IUserMapper userMapper;
    private final IUserService userService;
    @Override
    public List<Comment> getByArticleId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticleId, id);
        return list(queryWrapper).stream().peek(comment -> {
            // 通过articleId查询Article
            comment.setArticle(articleMapper.selectById(comment.getArticleId()));
            // 通过userId查询User
            comment.setAuthor(userMapper.selectById(comment.getAuthorId()));
            // 通过parentId查询Comment
            if (comment.getParentId() != null) {
                comment.setParent(commentMapper.selectById(comment.getParentId()));
            }
        }).toList();
    }

    @Override
    @Transactional
    public Comment saveCommentAndChangeCounts(Comment comment) {
        int count = 1;
        Article a = articleMapper.selectById(comment.getArticle().getId());
        a.setCommentCounts(a.getCommentCounts() + count);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getByName(username);

        comment.setAuthor(user);
        comment.setCreateDate(new Date());

        //设置level
        if(null == comment.getParent()){
            comment.setLevel("0");
        }else{
            if(null == comment.getToUser()){
                comment.setLevel("1");
            }else{
                comment.setLevel("2");
            }
        }
        save(comment);
        return comment;
    }
}
