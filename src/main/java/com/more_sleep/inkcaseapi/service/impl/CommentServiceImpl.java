package com.more_sleep.inkcaseapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.more_sleep.inkcaseapi.entity.Category;
import com.more_sleep.inkcaseapi.entity.Comment;
import com.more_sleep.inkcaseapi.mapper.IArticleMapper;
import com.more_sleep.inkcaseapi.mapper.ICategoryMapper;
import com.more_sleep.inkcaseapi.mapper.ICommentMapper;
import com.more_sleep.inkcaseapi.mapper.IUserMapper;
import com.more_sleep.inkcaseapi.service.ICategoryService;
import com.more_sleep.inkcaseapi.service.ICommentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
}
