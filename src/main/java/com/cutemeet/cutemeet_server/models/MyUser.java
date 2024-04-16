package com.cutemeet.cutemeet_server.models;

import jakarta.persistence.*;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@Table(name = "users_accounts")
public class MyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phoneNumber;
    private String password;

    private String name;
    private String middleName;
    private String surname;

    private String roles;

    @OneToOne
    @JoinColumn(name = "account_data")
    private MyUserAccountData accountData;

    public QuestionnaireData getQuestionnaireData(){
        return new QuestionnaireData(userName, getFIO(), accountData.getEducationPlace());
    }

    public String getFIO(){
        String FIO = surname + " " + name;
        if(middleName != null && !middleName.isEmpty() && !middleName.equals("null"))
            FIO += " " + middleName;
        return FIO;
    }


    public int calculateAge() {
        Calendar birthCal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        Date birthDate = null;
        try {
            birthDate = sdf.parse(accountData.getBirthdayDate());
        } catch (ParseException e) {
            return -1;
        }
        birthCal.setTime(birthDate);
        Calendar nowCal = Calendar.getInstance();
        int age = nowCal.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
        int monthDiff = nowCal.get(Calendar.MONTH) - birthCal.get(Calendar.MONTH);
        if (monthDiff < 0 || (monthDiff == 0 && nowCal.get(Calendar.DAY_OF_MONTH) < birthCal.get(Calendar.DAY_OF_MONTH))) {
            age--;
        }
        return age;
    }
}