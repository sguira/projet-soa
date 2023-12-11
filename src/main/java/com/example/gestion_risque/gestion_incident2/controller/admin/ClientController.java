package com.example.gestion_risque.gestion_incident2.controller.admin;

import com.example.gestion_risque.gestion_incident2.entity.Error;
import com.example.gestion_risque.gestion_incident2.entity.Loger;
import com.example.gestion_risque.gestion_incident2.entity.StatUser;
import com.example.gestion_risque.gestion_incident2.entity.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestion_risque.gestion_incident2.Repository.TicketRepository;
import com.example.gestion_risque.gestion_incident2.Repository.UserRepository;
import com.example.gestion_risque.gestion_incident2.email.EmailBody;
import com.example.gestion_risque.gestion_incident2.email.EmailServiceImp;
import com.example.gestion_risque.gestion_incident2.entity.User;

@RestController
@CrossOrigin("*")
public class ClientController {

    @Autowired(required = true)
    private UserRepository userR;

    @Autowired
    EmailServiceImp sender = new EmailServiceImp();

    @Autowired(required = true)
    private TicketRepository ticketR;

    // enregistrer un nouveau compte
    @PostMapping(path = "/save_user")
    ResponseEntity<?> saveUser(@RequestBody User user) {
        Error e = new Error();

        for (var user_ : userR.findAll()) {
            if (user_.getEmail() != null) {
                if (user_.getEmail().equals(user.getEmail())) {
                    e.setMessage("EXIST");
                    e.setStatusCode(500);
                    return new ResponseEntity<Error>(e, HttpStatus.OK);
                }
            }
        }

        UUID uuid = UUID.randomUUID();

        user.setToken_confirm(uuid.toString());

        EmailBody emailBody = new EmailBody();

        emailBody.setBody("CREATION DE COMPTE SUR HUB-SERVICE");
        String message = "Salut M/Mme " + user.getName()
                + " Nous vous souhaitons la bienvenue sur notre plateforme HUB-SERVICE\n" +
                "Nous vous envoyons cet mail suite à la création de votre nouveau compte" +
                "\n Veuillez utiliser ce mot de passe afin de pouvoir confirmer la création du compte   code de confirmation: "
                + uuid.toString();
        emailBody.setMessage(message);
        emailBody.setRecipient(user.getEmail());

        String res = sender.sendSimpleMessage(emailBody, user.getEmail());
        if (res.equals("Mail Sent Successfully...")) {
            Error ouput = new Error("CREE", 200);
            userR.save(user);
            return new ResponseEntity<Error>(ouput, HttpStatus.CREATED);
        } else {
            Error e_ = new Error("E-Mail", 500);
            return new ResponseEntity<>(e_, HttpStatus.OK);
        }

    }

    // confirmation de creation de compte
    @GetMapping(path = "confirm-compte/{email}/{pwd}")
    ResponseEntity confirmation(@PathVariable(name = "email") String email, @PathVariable(name = "pwd") String pwd) {
        for (var e : userR.findAll()) {
            if (e.getEmail().equals(email) && e.getToken_confirm().equals(pwd)) {
                e.setValide(true);
                e.setToken_confirm("");
                return new ResponseEntity<User>(userR.save(e), HttpStatus.CREATED);

            }

        }
        Error e = new Error("IMPOSSIBLE", 404);
        return new ResponseEntity<Error>(e, HttpStatus.OK);
    }

    // method pour authentification
    @GetMapping(path = "/login/{email}/{password}")
    ResponseEntity<?> loginUser(@PathVariable(name = "email") String email,
            @PathVariable(name = "password") String password) {
        for (var i : userR.findAll()) {
            if (i.getEmail() != null) {
                if (i.getEmail().equals(email) && i.getPassword().equals(password)) {
                    System.out.println(i.getEmail());
                    if (i.valide == true || i.getRole().equals("TECHNICIEN")) {
                        if (i.getRole().equals("TECHNICIEN")) {
                            Loger loger = new Loger(i.getId(), "TECHNICIEN");
                            return new ResponseEntity<Loger>(loger, HttpStatus.OK);
                        } else {
                            Loger loger = new Loger(i.getId(), "USER");
                            return new ResponseEntity<Loger>(loger, HttpStatus.OK);
                        }
                    } else {
                        return new ResponseEntity<>(-2, HttpStatus.OK);
                    }
                }
            }
        }
        return new ResponseEntity<Integer>(-1, HttpStatus.OK);
    }

    @GetMapping(path = "/users")
    ResponseEntity<List<User>> getAllUser() {
        return new ResponseEntity<List<User>>(userR.findAll(), HttpStatus.OK);
    }

    /*
     * Retourne quelques informations sur un utilisateur ou un technicien
     */
    @GetMapping(path = "/info_user/{id}")
    ResponseEntity<User> getInfoUser(@PathVariable String id) {
        User user = userR.findById(id).get();
        User u = new User();
        u.setId(id);
        u.setNumber(user.getNumber());
        u.setName(user.getName());
        u.setEmail(user.getEmail());
        u.setAdresse(user.getAdresse());
        return new ResponseEntity<User>(u, HttpStatus.OK);
    }

    @GetMapping(path = "tickets/{id}")
    ResponseEntity<List<Ticket>> fetchTicketById(@PathVariable String id) {
        List<Ticket> ticket = new ArrayList<Ticket>();
        for (Ticket t : ticketR.findAll()) {
            if (t.getUser_id() != null) {
                if (t.getUser_id().equals(id)) {
                    ticket.add(0, t);
                }
            }
        }
        return new ResponseEntity<List<Ticket>>(ticket, HttpStatus.OK);
    }

    /*
     * Méthode qui retourne quelques statistiques du client
     */
    @GetMapping(path = "get_statistique/{id}")
    ResponseEntity<StatUser> statistique(@PathVariable String id) {
        List<Ticket> ticket = ticketR.findAll();
        int total = 0;
        int pas = 0;
        int encours = 0;
        int terminer = 0;
        for (Ticket t : ticket) {

            if (t.getUser_id() != null) {
                if (t.getUser_id().equals(id)) {
                    total += 1;
                    if (t.getStatus().equals("Nouveau")) {
                        pas += 1;

                    }
                    if (t.getStatus().equals("En cours")) {
                        encours += 1;
                    }
                    if (t.getStatus().equals("Terminer") || t.getStatus().equals("Cloturer")) {
                        terminer += 1;
                    }
                }
            }
        }
        StatUser stat = new StatUser();

        stat.setNombreTerminer(terminer);
        stat.setNombreTotal(total);
        stat.setTicketEnAttente(pas);
        stat.setTicketEncours(encours);
        return new ResponseEntity<StatUser>(stat, HttpStatus.OK);
    }

    /*
     * cette méthode rétourne un seul ticket à partir de l'id du ticket
     */
    @GetMapping(path = "single_ticket/{id}")
    ResponseEntity<Optional<Ticket>> getTicketById(@PathVariable String id) {
        return new ResponseEntity<Optional<Ticket>>(ticketR.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = "dernier-ticket/{id}")
    List<Ticket> dernierTicket(@PathVariable String id) {
        List<Ticket> output = new ArrayList<Ticket>();
        List<Ticket> t = ticketR.findAll();
        for (var i = t.size() - 1; i >= 0 && output.size() < 3; i--) {
            if (t.get(i).getUser_id().equals(id)) {
                output.add(t.get(i));
            }
        }
        return output;

    }

    @PostMapping(path = "update_user")
    ResponseEntity<User> updateUser(@RequestBody User u) {
        User user = userR.findById(u.getId()).get();
        user.setName(u.getName());
        user.setNumber(u.getNumber());
        user.setAdresse(u.getAdresse());

        return new ResponseEntity<User>(userR.save(user), HttpStatus.OK);
    }
}
