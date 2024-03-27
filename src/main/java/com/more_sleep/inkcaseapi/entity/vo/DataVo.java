package com.more_sleep.inkcaseapi.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataVo implements Serializable {

    private Integer year;

    private Integer month;

    private Integer count;
}
