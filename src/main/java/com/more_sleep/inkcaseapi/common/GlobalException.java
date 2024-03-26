package com.more_sleep.inkcaseapi.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理
 * @Author: lbj
 * @Date: 2024/3/24
 */
// @ControllerAdvice注解是用来配置控制器通知的，其自身使用@Controller注解，用于定义控制器的全局通知。
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalException {
    /**
     * 异常处理
     */

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.error(ex.getMessage());
        if (ex.getMessage().contains("Duplicate entry")) {
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return R.error(msg);
        }
        return R.error(Code.USER_HAS_EXISTED);
    }

    @ExceptionHandler(CustomException.class)
    public R<String> CustomExceptionHandler(CustomException ex) {
        log.error(ex.getMessage());
        return R.error(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public R<String> otherExceptionHandler(Exception ex) {
        log.error("otherException");
        log.error("Error ease:" + ex.getMessage());
        ex.printStackTrace();
        return R.error("该功能未开放");
    }
}
