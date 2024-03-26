package com.more_sleep.inkcaseapi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.more_sleep.inkcaseapi.entity.Tag;
import com.more_sleep.inkcaseapi.entity.vo.TagVo;
import com.more_sleep.inkcaseapi.mapper.ITagMapper;
import com.more_sleep.inkcaseapi.service.ITagService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
@AllArgsConstructor
public class TagServiceImpl extends ServiceImpl<ITagMapper, Tag> implements ITagService {
    private ITagMapper tagMapper;

    @Override
    public List<TagVo> listWithDetail() {
        return tagMapper.listAllTagsByArticle();
    }

    @Override
    public List<TagVo> listWithDetail(Integer limit) {
        return tagMapper.listHotTagsByArticle(limit);
    }
}
