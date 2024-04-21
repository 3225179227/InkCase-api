package com.more_sleep.inkcaseapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;


/**
 * 文章内容
 * @Author: lbj
 * @Date: 2024/3/25
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("article_body")
public class ArticleBody implements Serializable {

    @Serial
    private static final long serialVersionUID = -7611409995977927628L;

    private Long id;

    private String content; // 内容

    private String contentHtml;
}
