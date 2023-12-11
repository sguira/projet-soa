package com.example.gestion_risque.gestion_incident2.controller.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestion_risque.gestion_incident2.Repository.TicketRepository;
import com.example.gestion_risque.gestion_incident2.entity.StatUser;
import com.example.gestion_risque.gestion_incident2.entity.Ticket;

@RestController

@CrossOrigin("*")
public class TechnicienController {

    @Autowired(required = true)
    private TicketRepository ticketR;

    // @GetMapping(path = "ticket-technicien/{id}")
    // ResponseEntity getTicketForTechnicien(@PathVariable String id) {
    // List<Ticket> ticket = new ArrayList<>();
    // for (var ti : ticketR.findAll()) {
    // if (ti.getTechnicien_id() != null) {
    // if (ti.getTechnicien_id().equals(id)) {
    // ticket.add(0, ti);
    // }
    // } else {
    // if (ti.getStatus().equals("Nouveau")) {
    // ticket.add(0, ti);
    // }
    // }
    // }
    // return new ResponseEntity<List<Ticket>>(ticket, HttpStatus.OK);
    // }

    // @GetMapping(path = "get_statistique/{id}")
    // ResponseEntity<StatUser> statistique(@PathVariable String id) {
    // List<Ticket> ticket = ticketR.findAll();
    // int total = 0;
    // int pas = 0;
    // int encours = 0;
    // int terminer = 0;
    // for (Ticket t : ticket) {

    // if (t.getUser_id() != null) {
    // if (t.getUser_id().equals(id)) {
    // total += 1;
    // if (t.getStatus().equals("Nouveau")) {
    // pas += 1;

    // }
    // if (t.getStatus().equals("En cours")) {
    // encours += 1;
    // }
    // if (t.getStatus().equals("Terminer") || t.getStatus().equals("Cloturer")) {
    // terminer += 1;
    // }
    // }
    // }
    // }
    // StatUser stat = new StatUser();

    // stat.setNombreTerminer(terminer);
    // stat.setNombreTotal(total);
    // stat.setTicketEnAttente(pas);
    // stat.setTicketEncours(encours);
    // return new ResponseEntity<StatUser>(stat, HttpStatus.OK);
    // }

}
