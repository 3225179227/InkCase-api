package com.more_sleep.inkcaseapi.controller;

import com.more_sleep.inkcaseapi.service.ITagService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/tag")
@AllArgsConstructor
public class TagController {
    private ITagService tagService;

    @GetMapping
    public Object list() {
        return tagService.list();
    }
}
