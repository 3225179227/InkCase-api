package com.more_sleep.inkcaseapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.more_sleep.inkcaseapi.entity.Category;
import com.more_sleep.inkcaseapi.entity.Comment;
import com.more_sleep.inkcaseapi.mapper.ICategoryMapper;
import com.more_sleep.inkcaseapi.mapper.ICommentMapper;
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
    @Override
    public List<Comment> getByArticleId(Long id) {
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Comment::getArticle, id);
        return list(queryWrapper);
    }

}
