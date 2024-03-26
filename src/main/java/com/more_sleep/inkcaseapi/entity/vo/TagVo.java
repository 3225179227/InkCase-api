package com.more_sleep.inkcaseapi.entity.vo;

import com.more_sleep.inkcaseapi.entity.Tag;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 */

// 在生成 equals 和 hashCode 方法时，会调用父类的 equals 和 hashCode 方法
@Data
@AllArgsConstructor()
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)// 解决 lombok 的 @Data 注解导致的 equals 和 hashCode 方法没有父类属性的问题
@ToString(callSuper = true)// 解决 lombok 的 @Data 注解导致的 toString 方法没有父类属性的问题
public class TagVo extends Tag implements Serializable {
    @Serial
    private static final long serialVersionUID = 5059212992497947120L;

    private Integer articles;
}
