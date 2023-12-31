package com.example.gestion_risque.gestion_incident2.email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailBody {

    public String body;
    public String message;
    public String recipient;
    public String attachement;
}
