package com.cutemeet.cutemeet_server.repository;

import com.cutemeet.cutemeet_server.models.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    Optional<MyUser> findUserByUserName(String userName);
    Optional<MyUser> findUserByEmail(String email);
    Optional<MyUser> findUserByPhoneNumber(String number);
}
