package com.more_sleep.inkcaseapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.more_sleep.inkcaseapi.entity.Comment;

import java.util.List;

/**
 *
 */
public interface ICommentService extends IService<Comment> {
    List<Comment> getByArticleId(Long id);

    Comment saveCommentAndChangeCounts(Comment comment);
}
