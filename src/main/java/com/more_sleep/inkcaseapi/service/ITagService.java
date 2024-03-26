package com.more_sleep.inkcaseapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.more_sleep.inkcaseapi.entity.Tag;
import com.more_sleep.inkcaseapi.entity.vo.TagVo;

import java.util.List;

/**
 *
 */

public interface ITagService extends IService<Tag> {
    List<TagVo> listWithDetail();

    List<TagVo> listWithDetail(Integer limit);
}
