package com.example.gestion_risque.gestion_incident2;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.management.Query;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestion_risque.gestion_incident2.Repository.TicketRepository;
import com.example.gestion_risque.gestion_incident2.Repository.UserRepository;
import com.example.gestion_risque.gestion_incident2.email.EmailBody;
import com.example.gestion_risque.gestion_incident2.entity.Error;

import com.example.gestion_risque.gestion_incident2.entity.StatUser;
import com.example.gestion_risque.gestion_incident2.entity.Tache;
import com.example.gestion_risque.gestion_incident2.entity.Ticket;
import com.example.gestion_risque.gestion_incident2.entity.User;

import com.example.gestion_risque.gestion_incident2.entity.Loger;
import com.example.gestion_risque.gestion_incident2.email.EmailServiceImp;

@RestController
@CrossOrigin("*")

public class Controller {

    @Autowired(required = true)
    private UserRepository userR;

    @Autowired(required = true)
    private TicketRepository ticketR;

    @Autowired
    EmailServiceImp sender = new EmailServiceImp();

    @PostMapping(path = "send_message")
    Error sendMessage(@RequestBody EmailBody email) {
        System.out.println("Message:\n\n" + email.getMessage());
        // changer le mail pour mettre celui de l'entreprise
        String res = sender.sendSimpleMessage(email, "sguira96@gmail.com");
        Error e = new Error(res, 200);
        return e;
    }

}
