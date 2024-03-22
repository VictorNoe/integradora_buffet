package com.buffet.buffet.services.mailservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private Environment env;
    @Autowired
    private JavaMailSender javaMailSender;
    public MailService(Environment environment){
        this.env =environment;
    }
    public void sendMail(String mailAddress,String title,String mailMessage){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(env.getProperty("spring.mail.username"));
        message.setTo(mailAddress);
        message.setSubject(title);
        message.setText(mailMessage);
        javaMailSender.send(message);
    }
}
