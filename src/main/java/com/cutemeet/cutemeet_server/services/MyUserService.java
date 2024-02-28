package com.cutemeet.cutemeet_server.services;

import com.cutemeet.cutemeet_server.models.MyUser;
import com.cutemeet.cutemeet_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserService {
    private UserRepository repository;
    private PasswordEncoder passwordEncoder;

    public void addUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        repository.save(user);
    }

    public boolean isPasswordCorrect(String username, String password){
        Optional<MyUser> user = repository.findUserByUserName(username);
        String encodedPassword = passwordEncoder.encode(password);
        MyUser euser = user.orElse(null);
        if(euser == null) return  false;
        return passwordEncoder.matches(password, euser.getPassword());
    }
}
