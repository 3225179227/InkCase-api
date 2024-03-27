package com.more_sleep.inkcaseapi.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


/**
 * 自定义元数据对象处理器
 */

@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("common insert");
        log.info(metaObject.toString());
        
        metaObject.setValue("createDate", LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("common update");
        log.info(metaObject.toString());
        System.out.println("线程id：" + Thread.currentThread().getId());

        metaObject.setValue("updateTime", LocalDateTime.now());
    }
}
