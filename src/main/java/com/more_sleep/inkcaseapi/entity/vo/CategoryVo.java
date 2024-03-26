package com.more_sleep.inkcaseapi.entity.vo;


import com.more_sleep.inkcaseapi.entity.Category;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author shimh
 * <p>
 * 2018年1月29日
 */

@Data
@AllArgsConstructor()
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryVo extends Category implements Serializable {

    /**
     *
     */
    @Serial
    private static final long serialVersionUID = -2975739216517528014L;


    private int articles;

}
