package com.cutemeet.cutemeet_server.services;

import com.cutemeet.cutemeet_server.models.EmailMessage;
import com.cutemeet.cutemeet_server.repository.InMemoryEmailsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private InMemoryEmailsDAO repository;

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}") private String sender;

    public String sendVerificationCode(String recipient){
        String code = generateCode(4);
        EmailMessage m2s = new EmailMessage(recipient, code);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(recipient);
        message.setSubject("Verification code.");
        message.setText("The code: " + code);

        try {
            javaMailSender.send(message);
            repository.saveVerificationMessage(m2s);
        }catch (Exception e){
            System.out.println(e);
            return "The message has not been sent!";
        }
        return "The message has been sent.";
    }

    public boolean checkCode(String email, String code){
        return repository.checkCode(email, code);
    }

    private String generateCode(int len){
        String charVars = "0123456789";
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i < len; i++){
            result.append(charVars.charAt(random.nextInt(charVars.length())));
        }
        return result.toString();
    }
}
