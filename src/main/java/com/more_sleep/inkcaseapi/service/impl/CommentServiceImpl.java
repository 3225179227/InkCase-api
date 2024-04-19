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
        queryWrapper.eq(Comment::getLevel, "0");
        // 根据创建时间倒序
        queryWrapper.orderByDesc(Comment::getCreateDate);
        return list(queryWrapper).stream().peek(comment -> {
            // 通过articleId查询Article
            comment.setArticle(articleMapper.selectById(comment.getArticleId()));
            // 通过userId查询User
            comment.setAuthor(userMapper.selectById(comment.getAuthorId()));
            // 通过parentId查询Comment
            if (comment.getParentId() != null) {
                comment.setParent(commentMapper.selectById(comment.getParentId()));
            }
            // 通过toUserId查询User
            if (comment.getToUserId() != null) {
                comment.setToUser(userMapper.selectById(comment.getToUserId()));
            }
            // 查询子评论
            LambdaQueryWrapper<Comment> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Comment::getParentId, comment.getId());
            List<Comment> children = list(queryWrapper1).stream().peek(child -> {
                child.setAuthor(userMapper.selectById(child.getAuthorId()));
                if (child.getToUserId() != null) {
                    child.setToUser(userMapper.selectById(child.getToUserId()));
                }
            }).toList();
            comment.setChildrens(children);
        }).toList();
    }

    @Override
    @Transactional
    public Comment saveCommentAndChangeCounts(Comment comment) {
        int count = 1;
        Article a = articleMapper.selectById(comment.getArticleId());
        a.setCommentCounts(a.getCommentCounts() + count);

        articleMapper.updateById(a);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getByName(username);

        comment.setAuthor(user);
//        comment.setCreateDate(new Date());

        //设置level
        if(null == comment.getParent()){
            comment.setLevel("0");
        }else{
            if(null == comment.getToUser()){
                comment.setLevel("1");
            }else{
                comment.setLevel("2");
                System.out.println("lbj!!!:" + comment.getToUser().getId());
                comment.setToUserId(comment.getToUser().getId());
            }
        }
        save(comment);
        return comment;
    }
}
