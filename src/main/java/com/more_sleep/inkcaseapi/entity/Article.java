package com.more_sleep.inkcaseapi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 文章
 * @Author: lbj
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("me_article")
public class Article implements Serializable {

    public static final int Article_TOP = 1;

    public static final int Article_Common = 0;

    @Serial
    private static final long serialVersionUID = -4470366380115322213L;

    private Long id;

    @NotBlank
    private String title;

    @NotBlank
    private String summary;


//    @ManyToOne(fetch = FetchType.LAZY)
    @TableField(exist = false)
    private User author;

    private Long authorId;

//    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true)
    @TableField(exist = false)
    private ArticleBody body;

    private Long bodyId;


//    @ManyToOne(fetch = FetchType.LAZY)

    // 多对一
    @TableField(exist = false)
    private Category category;

    private Long categoryId;

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "me_article_tag",
//            joinColumns = {@JoinColumn(name = "article_id")},
//            inverseJoinColumns = {@JoinColumn(name = "tag_id")})

    // 多对多
    @TableField(exist = false)
    private List<Tag> tags;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "article", orphanRemoval = true)
//    @JsonBackReference
    @TableField(exist = false)
    private List<Comment> comments;

    private int commentCounts;

    private int viewCounts;

    /**
     * 置顶
     */
    private int weight = Article_Common;

    /**
     * 创建时间
     */
//    @JSONField(format = "yyyy.MM.dd HH:mm")
    private Date createDate;

    private String tagsStr;
}
