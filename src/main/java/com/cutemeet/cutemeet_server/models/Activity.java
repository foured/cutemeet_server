package com.cutemeet.cutemeet_server.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String senderName;
    private String senderContact;
    private String date;
    private String location;
    private String tags;
}
