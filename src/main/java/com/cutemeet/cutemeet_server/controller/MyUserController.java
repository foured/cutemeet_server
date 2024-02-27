package com.cutemeet.cutemeet_server.controller;

import com.cutemeet.cutemeet_server.models.MyUser;
import com.cutemeet.cutemeet_server.services.MyUserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/new_user")
@AllArgsConstructor
public class MyUserController {
    private MyUserService myUserService;

    @PostMapping()
    public String addUser(@RequestBody MyUser user){
        myUserService.addUser(user);
        return "User is saved!";
    }
}
