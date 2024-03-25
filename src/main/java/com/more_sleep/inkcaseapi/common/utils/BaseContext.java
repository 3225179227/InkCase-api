package com.more_sleep.inkcaseapi.common.utils;


/**
 * 基于ThreadLocal工具类，保存用户id
 * @Author: lbj
 * @Date: 2024/3/24
 */
public class BaseContext {
    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        System.out.println(Thread.currentThread().getId());
        System.out.println("BaseContext: 保存id：" + id);
        threadLocal.set(id);
    }

    public static Long getCurrentId() {
        System.out.println(Thread.currentThread().getId());
        System.out.println("BaseContext: 取出id：" + threadLocal.get());
        return threadLocal.get();
    }
}
