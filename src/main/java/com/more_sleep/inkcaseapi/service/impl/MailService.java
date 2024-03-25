package com.more_sleep.inkcaseapi.service.impl;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import java.util.Date;

@Slf4j
@Service
public class MailService {
//    private static final Logger logger = LoggerFactory.getLogger(MailService.class);

    /**
     * 注入邮件工具类
     */
    private final JavaMailSenderImpl javaMailSender;

    @Value("${spring.mail.username}")
    private String sendMailer;

    public MailService(JavaMailSenderImpl javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    /**
     * 检查邮件信息
     * @param to 收件人邮箱，可以是多个，以逗号分隔
     * @param subject 邮件主题
     * @param text 邮件内容
     * @throws RuntimeException 如果收件人、主题或内容为空，则抛出运行时异常
     */
    private void checkMail(String to, String subject, String text) {
        if(StringUtils.isEmpty(to)){
            throw new RuntimeException("邮件收信人不能为空");
        }
        if(StringUtils.isEmpty(subject)){
            throw new RuntimeException("邮件主题不能为空");
        }
        if(StringUtils.isEmpty(text)){
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    /**
     * 发送纯文本邮件
     * @param to 收件人邮箱，可以是多个，以逗号分隔
     * @param subject 邮件主题
     * @param text 邮件内容
     */
    public void sendTextMailMessage(String to, String subject, String text) {
        try {
            checkMail(to, subject, text);
            // true 代表支持复杂的类型
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage(), true);
            // 邮件发信人
            mimeMessageHelper.setFrom(sendMailer);
            // 邮件收信人，可以是多个
            mimeMessageHelper.setTo(to.split(","));
            // 邮件主题
            mimeMessageHelper.setSubject(subject);
            // 邮件内容
            mimeMessageHelper.setText(text);
            // 邮件发送时间
            mimeMessageHelper.setSentDate(new Date());

            // 发送邮件
//            javaMailSender.send(mimeMessageHelper.getMimeMessage());
            System.out.println("发送邮件成功：" + sendMailer + " -> " + to);
        } catch (MessagingException e) {
            log.error(e.getMessage());
            System.out.println("发送邮件失败：" + e.getMessage());
        }
    }
}
