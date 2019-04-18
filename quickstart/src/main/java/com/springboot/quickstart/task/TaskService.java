package com.springboot.quickstart.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    private MailService mailService;

    @Scheduled(cron = "0 8 10 ? * *")
    public void proces(){
        mailService.sendMail("16422802@qq.com","定时邮件测试","这是一个来自软件1721刘磊的定时邮件测试,定时到10点8分");
        System.out.println("success");
    }
}