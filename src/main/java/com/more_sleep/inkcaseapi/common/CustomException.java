package com.more_sleep.inkcaseapi.common;

/**
 * 自定义异常
 * @Author: lbj
 * @Date: 2024/3/24
 */

public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
