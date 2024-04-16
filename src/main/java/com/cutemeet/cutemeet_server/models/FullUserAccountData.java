package com.cutemeet.cutemeet_server.models;

import jakarta.persistence.Column;

public class FullUserAccountData {

    public FullUserAccountData(MyUser user){
        username = user.getUserName();
        FIO = user.getFIO();
        photoData = user.getAccountData().getPhotoData();
        tags = user.getAccountData().getTags();
        educationPlace = user.getAccountData().getEducationPlace();
        description = user.getAccountData().getDescription();
        birthdayDate = user.getAccountData().getBirthdayDate();
        tgLink = user.getAccountData().getTgLink();
    }

    public String username;
    public String FIO;
    public byte[] photoData;
    public String tags;
    public String educationPlace;
    public String description;
    public String birthdayDate;
    public String tgLink;
}
