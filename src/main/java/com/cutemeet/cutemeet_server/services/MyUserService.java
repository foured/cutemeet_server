package com.cutemeet.cutemeet_server.services;

import com.cutemeet.cutemeet_server.models.MyUser;
import com.cutemeet.cutemeet_server.models.MyUserAccountData;
import com.cutemeet.cutemeet_server.repository.UserAccountDataRepository;
import com.cutemeet.cutemeet_server.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MyUserService {
    private UserRepository repository;
    private UserAccountDataRepository userAccountDataRepository;
    private PasswordEncoder passwordEncoder;

    public void addUser(MyUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles("ROLE_USER");
        repository.save(user);
    }

    public boolean isPasswordCorrect(String username, String password){
        Optional<MyUser> user = repository.findUserByUserName(username);
        MyUser euser = user.orElse(null);
        if(euser == null) return  false;
        return passwordEncoder.matches(password, euser.getPassword());
    }

    public String checkEmailAndPhoneNumber(String email, String phoneNumber){
        Optional<MyUser> phUser = repository.findUserByPhoneNumber(phoneNumber);
        if(phUser.isPresent()) return "Пользователь с таким номером толефона уже существует.";

        Optional<MyUser> eUser = repository.findUserByEmail(email);
        if(eUser.isPresent()) return "Пользователь с такой почтой уже существует.";

        return "";
    }

    public String checkUsername(String username){
        Optional<MyUser> unUser = repository.findUserByUserName(username);
        if(unUser.isPresent()) return "Пользователь с таким псевдонимом уже существует.";

        return  "";
    }

    public String canUserBeCreated(MyUser newUser){
        String username = newUser.getUserName();
        Optional<MyUser> unUser = repository.findUserByUserName(username);
        if(unUser.isPresent()) return "Пользователь с таким username уже существует.";

        String email = newUser.getEmail();
        Optional<MyUser> eUser = repository.findUserByEmail(email);
        if(eUser.isPresent()) return "Пользователь с таким email уже существует.";

        String phoneNumber = newUser.getPhoneNumber();
        Optional<MyUser> phUser = repository.findUserByPhoneNumber(phoneNumber);
        if(phUser.isPresent()) return "Пользователь с таким phone number уже существует.";

        return "";
    }

    public String setUserAccountData(String username, MyUserAccountData accountData){
        Optional<MyUser> oUser = repository.findUserByUserName(username);

        if(oUser.isPresent()){
            MyUser user = oUser.get();
            userAccountDataRepository.save(accountData);
            user.setAccountData(accountData);
            repository.save(user);
            return "Saved.";
        }
        else{
            return "No user with username: " + username;
        }
    }

    public String getPhoto(String username){
        Optional<MyUser> oUser = repository.findUserByUserName(username);

        if(oUser.isPresent()){
            MyUser user = oUser.get();
            return Base64.getEncoder().encodeToString(user.getAccountData().getPhotoData());
        }
        else{
            return "No user with username: " + username;
        }
    }

    public MyUserAccountData getAccountData(String username){
        Optional<MyUser> oUser = repository.findUserByUserName(username);

        if(oUser.isPresent()){
            MyUserAccountData uad = oUser.get().getAccountData();
            uad.setPhotoData(null);
            return uad;
        }
        else{
            return null;
        }
    }
}
