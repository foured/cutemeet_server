package com.cutemeet.cutemeet_server.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users_accounts")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String email;
    private String phoneNumber;
    private String password;

    private String name;
    private String middleName;
    private String surname;

    private String roles;
}