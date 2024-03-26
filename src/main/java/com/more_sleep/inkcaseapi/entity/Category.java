package com.more_sleep.inkcaseapi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章分类
 * @Author: lbj
 * @Date: 2024/3/25
 */


@TableName("me_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    /**
     *
     */
    private static final long serialVersionUID = 5025313969040054182L;

    @NotBlank
    @TableField("categoryname")
    private String categoryName;

    private String description;

    @NotBlank
    private String avatar;
}
