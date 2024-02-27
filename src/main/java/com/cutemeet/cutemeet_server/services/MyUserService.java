package com.cutemeet.cutemeet_server.services;

import com.cutemeet.cutemeet_server.models.MyUser;
import com.cutemeet.cutemeet_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
