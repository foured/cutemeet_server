package com.cutemeet.cutemeet_server.repository;

import com.cutemeet.cutemeet_server.models.EmailMessage;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class InMemoryEmailsDAO {
    private final List<EmailMessage> verificationMessages = new ArrayList<>();

    public void saveVerificationMessage(EmailMessage message){
        verificationMessages.add(0, message);
    }

    public boolean checkCode(String email, String code){
        EmailMessage message = verificationMessages.stream()
                .filter(msg -> Objects.equals(msg.email, email))
                .findFirst()
                .orElse(null);

        if(message == null) return false;
        return Objects.equals(message.message, code);
    }
}
