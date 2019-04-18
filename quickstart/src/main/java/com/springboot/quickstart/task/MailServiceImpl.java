package com.springboot.quickstart.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService {
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendMail(String to, String subject, String content) {
        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom("1723109431@qq.com");//发起者
        mailMessage.setTo("16422802@qq.com");//接受者
        mailMessage.setSubject("定时邮件测试");
        mailMessage.setText("这是一个来自软件1721刘磊的定时邮件测试,定时到10点8分");
        try {
            mailSender.send(mailMessage);
            System.out.println("发送简单邮件");
        }catch (Exception e){
            System.out.println("发送简单邮件失败");
        }
    }

}