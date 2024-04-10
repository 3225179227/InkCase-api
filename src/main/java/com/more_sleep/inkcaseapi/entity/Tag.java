package com.more_sleep.inkcaseapi.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * 文章标签
 * @Author: lbj
 * @Date: 2024/3/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("me_tag")
public class Tag implements Serializable {
    @Serial
    private static final long serialVersionUID = 5025313969040054182L;

    @NotBlank
    private String tagName;

    @NotBlank
    private String avatar;
}
