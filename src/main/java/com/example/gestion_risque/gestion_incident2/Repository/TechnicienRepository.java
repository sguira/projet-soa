package com.example.gestion_risque.gestion_incident2.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.gestion_risque.gestion_incident2.entity.Technicien;

public interface TechnicienRepository extends MongoRepository<Technicien, String> {

}
