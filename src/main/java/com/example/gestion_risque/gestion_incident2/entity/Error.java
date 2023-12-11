package com.example.gestion_risque.gestion_incident2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Error {
    public String message;
    public int statusCode;
}
