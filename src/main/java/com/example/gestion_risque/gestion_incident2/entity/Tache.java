package com.example.gestion_risque.gestion_incident2.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Tache implements Serializable {

    public String description;
    public boolean etat = false;

}
