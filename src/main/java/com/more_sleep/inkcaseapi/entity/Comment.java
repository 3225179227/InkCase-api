package com.more_sleep.inkcaseapi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 评论
 *
 * @author shimh
 * <p>
 * 2018年1月30日
 */

@TableName("me_comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment implements Serializable {

    @Serial
    private static final long serialVersionUID = 7346271954336613146L;

    @NotBlank
    private String content;


//    @ManyToOne(fetch = FetchType.LAZY)
    @TableField(exist = false)
    private User author;

    private Long authorId;

    /**
     * 类型 0 文章的评论 1 评论的评论 2 评论的回复 @
     */
    private String level;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;


//    @ManyToOne(fetch = FetchType.LAZY)
//    @JsonManagedReference
    @TableField(exist = false)
    private Article article;

    private Long articleId;

    private Long parentId;

//    @OneToMany
    // @JsonManagedReference这个注解是为了解决双向关联导致的无限递归问题
    // JsonManagedReference这个注解的作用是表示这是一个“主”对象，需要被序列化。
//    @JsonManagedReference
    @TableField(exist = false)
    private List<Comment> childrens;

//    @ManyToOne
//    @NotFound(action= NotFoundAction.IGNORE)
    // @JsonBackReference这个注解是为了解决双向关联导致的无限递归问题
    // 它的作用是在序列化的时候表示这是一个“从”对象忽略这个属性不序列化，避免无限递归
//    @JsonBackReference
    @TableField(exist = false)
    private Comment parent;


//    @ManyToOne(fetch = FetchType.LAZY)
    @TableField(exist = false)
    private User toUser;

    @TableField("to_uid")
    private Long toUserId;

}
