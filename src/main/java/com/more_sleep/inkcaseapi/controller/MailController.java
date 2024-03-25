package com.more_sleep.inkcaseapi.controller;


import com.more_sleep.inkcaseapi.service.impl.MailService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/mail")
public class MailController {
    private final MailService mailService;
    private final RedisTemplate<Object, Object> redisTemplate;

    public MailController(MailService mailService, RedisTemplate<Object, Object> redisTemplate) {
        this.mailService = mailService;
        this.redisTemplate = redisTemplate;
    }

    @PostMapping("/sendmail")
    public void sendTextMail(@RequestBody Map<String, String> map){
//        System.out.println(name + to);
        String to = map.get("to");
        String code = String.valueOf(Math.random()).substring(2,8);
        // 这里设置为session缓存，优化为redis缓存
//        request.getSession().setAttribute(to,code);
        // redis缓存
        ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(to, code, 3, TimeUnit.MINUTES);


        System.out.println("Mail code: " + code);
        String subject = "Dear user:";
        String text = "Your code:" + code;
        mailService.sendTextMailMessage(to,subject,text);
    }
}