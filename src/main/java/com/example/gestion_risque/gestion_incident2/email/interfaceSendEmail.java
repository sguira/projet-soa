package com.example.gestion_risque.gestion_incident2.email;

public interface interfaceSendEmail {

    public String sendSimpleMessage(EmailBody email, String email_);

    public String sendEmailWithAttachment(EmailBody email);
}
