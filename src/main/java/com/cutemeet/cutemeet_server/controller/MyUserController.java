package com.cutemeet.cutemeet_server.controller;

import com.cutemeet.cutemeet_server.models.MyUserAccountData;
import com.cutemeet.cutemeet_server.services.MyUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class MyUserController {
    private MyUserService myUserService;

    @GetMapping("/get_username")
    private String getUsername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @PostMapping("/set_accountData")
    private String setAccountData(@RequestBody MyUserAccountData accountData){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String tagsLine = accountData.getTags().trim();
        tagsLine = tagsLine.toLowerCase();
        String[] tags = tagsLine.split("\\s*,\\s*");
        accountData.setTags(String.join(", ", tags));


        return myUserService.setUserAccountData(authentication.getName(), accountData);
    }

    @GetMapping("/get_photo")
    private String getPhoto(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return myUserService.getPhoto(authentication.getName());
    }

    @GetMapping("/get_accountData")
    private MyUserAccountData getAccountData(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return myUserService.getAccountData(authentication.getName());
    }
}
