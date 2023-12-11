package com.example.gestion_risque.gestion_incident2.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestion_risque.gestion_incident2.Repository.TicketRepository;
import com.example.gestion_risque.gestion_incident2.entity.Error;
import com.example.gestion_risque.gestion_incident2.entity.Tache;
import com.example.gestion_risque.gestion_incident2.entity.Ticket;

@RestController
@CrossOrigin("*")
public class TicketController {

    @Autowired(required = true)
    private TicketRepository ticketR;

    /*
     * Ajout d'une nouvelle tache à un ticket
     */

    @PostMapping(path = "/save_ticket")
    ResponseEntity<Ticket> saveTicket(@RequestBody Ticket ticket) {

        return new ResponseEntity<Ticket>(ticketR.save(ticket), HttpStatus.CREATED);
    }

    @PostMapping(path = "add_taches/{id}")
    ResponseEntity<?> ajouterTache2(@RequestBody Tache tache, @PathVariable String id) {
        Ticket ticket = ticketR.findById(id).get();

        for (var t : ticket.getTache()) {
            if (t.getDescription() != null) {
                if (t.getDescription().equals(tache.getDescription())) {
                    Error e = new Error("EXIST", 500);
                    return new ResponseEntity<Error>(e, null);
                }
            }
        }
        ticket.ajouterTache(tache);
        return new ResponseEntity<Ticket>(ticketR.save(ticket), HttpStatus.OK);

    }

    /*
     * suppression d'une tache à partir de la description
     */
    @PostMapping(path = "remove_tache/{id}")
    ResponseEntity<Ticket> deleteTache(@RequestParam String tache, @PathVariable String id) {
        Ticket ticket = ticketR.findById(id).get();
        for (int i = 0; i < ticket.getTache().size(); i++) {
            if (ticket.getTache().get(i).getDescription().equals(tache)) {
                ticket.getTache().remove(i);
                return new ResponseEntity<Ticket>(ticketR.save(ticket), HttpStatus.OK);

            }
        }
        return new ResponseEntity<Ticket>(HttpStatus.OK);

    }

}
