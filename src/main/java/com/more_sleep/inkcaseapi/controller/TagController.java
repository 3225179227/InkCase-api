package com.more_sleep.inkcaseapi.controller;

import com.more_sleep.inkcaseapi.common.R;
import com.more_sleep.inkcaseapi.entity.Tag;
import com.more_sleep.inkcaseapi.entity.vo.TagVo;
import com.more_sleep.inkcaseapi.service.ITagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/tag")
@AllArgsConstructor
public class TagController {
    private ITagService tagService;

    @GetMapping
    public R<List<Tag>> list() {
        return R.success(tagService.list());
    }

    @GetMapping("/detail")
    public R<List<TagVo>> listWithDetail() {
        return R.success(tagService.listWithDetail());
    }

    @GetMapping("/hot")
    public R<List<TagVo>> hotWithDetail() {
        return R.success(tagService.listWithDetail(6));
    }

    @PostMapping
    public R<String> add(@RequestBody Tag tag) {
        if (tagService.save(tag)) {
            return R.success("添加标签成功");
        }
        return R.error("添加标签失败");
    }

    @DeleteMapping("/{id}")
    public R<String> delete(@PathVariable Long id) {
        if (tagService.removeById(id)) {
            return R.success("删除标签成功");
        }
        return R.error("删除标签失败");
    }
}
