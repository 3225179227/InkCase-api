package com.more_sleep.inkcaseapi.common;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * 通用返回结果类，服务端响应的数据都会封装成此对象
 * @param <T>
 *     T: 服务端响应的数据类型
 *
 * @Author: lbj
 * @Date: 2024/3/24
 */
@Data
public class R<T> implements Serializable {

    private Integer code; //状态码

    private String msg; //错误信息

    private T data; //数据

    private Map<Object, Object> map = new HashMap<>(); //动态数据

    public static <T> R<T> success(T object) {
        R<T> r = new R<>();
        r.data = object;
        r.code = Code.SUCCESS.getCode();
        return r;
    }

    public static <T> R<T> error(String msg) {
        R<T> r = new R<>();
        r.msg = msg;
        r.code = Code.ERROR.getCode();
        return r;
    }
    public static <T> R<T> error(Integer code, String msg) {
        R<T> r = new R<>();
        r.msg = msg;
        r.code = code;
        return r;
    }
    public static <T> R<T> error(Code code) {
        R<T> r = new R<>();
        r.msg = code.getMessage();
        r.code = code.getCode();
        return r;
    }

    public R<T> add(String key, Object value) {
        this.map.put(key, value);
        return this;
    }
}
