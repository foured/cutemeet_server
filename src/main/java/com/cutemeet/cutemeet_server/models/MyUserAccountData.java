package com.cutemeet.cutemeet_server.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users_accounts_data")
public class MyUserAccountData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private byte[] photoData;
    private String tags;
    private String educationPlace;
    private String description;
    private String birthdayDate;
    private String tgLink;
}
