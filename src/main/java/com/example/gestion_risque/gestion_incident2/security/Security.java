// package com.example.gestion_risque.gestion_incident2.security;

// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import
// org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import
// org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.web.SecurityFilterChain;

// // @Configuration
// // @EnableWebSecurity
// public class Security {

// // @Bean
// public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity)
// throws Exception {

// httpSecurity.formLogin(fm -> fm.permitAll(true));
// httpSecurity.authorizeHttpRequests(ar ->
// ar.requestMatchers("*/**").permitAll());
// // httpSecurity.authorizeHttpRequests(ar ->
// // ar.requestMatchers("/admin/**").authenticated());
// return httpSecurity.build();
// }

// }
