package com.example.gestion_risque.gestion_incident2.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.gestion_risque.gestion_incident2.entity.User;

public interface UserRepository extends MongoRepository<User, String> {

}
