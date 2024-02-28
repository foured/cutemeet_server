package com.cutemeet.cutemeet_server.controller;

import com.cutemeet.cutemeet_server.models.MyUser;
import com.cutemeet.cutemeet_server.services.EmailService;
import com.cutemeet.cutemeet_server.services.MyUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/operations")
@AllArgsConstructor
public class OperationsController {
    private MyUserService myUserService;
    private EmailService emailService;

    @PostMapping("/new_user")
    public String addUser(@RequestBody MyUser user){
        myUserService.addUser(user);
        return "User is saved!";
    }

    @GetMapping("/check_password")
    public boolean isPasswordCorrectPassword(@RequestParam String username, @RequestParam String password){
        return myUserService.isPasswordCorrect(username, password);
    }

    @GetMapping("/send_mail")
    public String sendMail(@RequestParam String recipient){
        return emailService.sendVerificationCode(recipient);
    }

    @GetMapping("/check_code")
    public boolean checkCode(@RequestParam String email, @RequestParam String code){
        return emailService.checkCode(email, code);
    }
}
