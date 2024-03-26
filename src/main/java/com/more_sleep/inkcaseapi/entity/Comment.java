package com.more_sleep.inkcaseapi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private static final long serialVersionUID = 7346271954336613146L;

    @NotBlank
    private String content;


//    @ManyToOne(fetch = FetchType.LAZY)
    private User author;

    /**
     * 类型 0 文章的评论 1 评论的评论 2 评论的回复 @
     */
    private String level;

    /**
     * 创建时间
     */
    @JSONField(format = "yyyy.MM.dd HH:mm")
    private Date createDate;


//    @ManyToOne(fetch = FetchType.LAZY)
    @JsonManagedReference
    private Article article;

//    @OneToMany
    // @JsonManagedReference这个注解是为了解决双向关联导致的无限递归问题
    // JsonManagedReference这个注解的作用是表示这是一个“主”对象，需要被序列化。
    @JsonManagedReference
    private List<Comment> childrens;

//    @ManyToOne
//    @NotFound(action= NotFoundAction.IGNORE)
    // @JsonBackReference这个注解是为了解决双向关联导致的无限递归问题
    // 它的作用是在序列化的时候表示这是一个“从”对象忽略这个属性不序列化，避免无限递归
    @JsonBackReference
    private Comment parent;


//    @ManyToOne(fetch = FetchType.LAZY)
    private User toUser;


}
