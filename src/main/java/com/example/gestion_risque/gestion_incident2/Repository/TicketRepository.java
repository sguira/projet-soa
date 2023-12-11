package com.example.gestion_risque.gestion_incident2.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.gestion_risque.gestion_incident2.entity.Ticket;

@CrossOrigin("*")
public interface TicketRepository extends MongoRepository<Ticket, String> {

    @Query("SELECT * from ticket ORDER BY ASK date")
    List<Ticket> findAllTicketsReverse() throws Exception;
}
    	