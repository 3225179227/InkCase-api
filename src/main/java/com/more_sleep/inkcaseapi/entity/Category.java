package com.more_sleep.inkcaseapi.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 文章分类
 * @Author: lbj
 * @Date: 2024/3/25
 */


@TableName("category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = 5025313969040054182L;

//    @JsonIgnore

    private Long id;

    @NotBlank
    @TableField("categoryname")

    private String categoryName;

    private String description;

    @NotBlank
    private String avatar;
}
