package com.example.gestion_risque.gestion_incident2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("*")
public class GestionIncident2Application {

	public static void main(String[] args) {
		SpringApplication.run(GestionIncident2Application.class, args);
	}

}
