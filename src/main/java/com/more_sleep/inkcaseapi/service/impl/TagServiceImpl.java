package com.more_sleep.inkcaseapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.more_sleep.inkcaseapi.entity.Tag;
import com.more_sleep.inkcaseapi.mapper.ITagMapper;
import com.more_sleep.inkcaseapi.service.ITagService;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class TagServiceImpl extends ServiceImpl<ITagMapper, Tag> implements ITagService {
}
