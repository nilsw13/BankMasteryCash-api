package com.nilsw13.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<?> greets(){

        LinkedHashMap<String, Object> infos = new LinkedHashMap<>();
        infos.put("Message" ,"Bienvenue sur l'api Bank Mastery Cash ---> ATTENTION CECI N'EST QU'UNE DEMO .. LES DONNÉES SONT FAUSSES ET UN GRANDS NOMBRE NE SONT PAS DYNAMIQUE");
        infos.put("Version", "1.0.0");
        infos.put("Auteur", "Nils Wenting");

        LinkedHashMap<String, String> endpoints = new LinkedHashMap<>();
        endpoints.put("List transactions", "/v1/transactions");
        endpoints.put("Add transaction", "/v1/add-transaction");
        endpoints.put("List savings account", "/v1/savings");
        endpoints.put("Add new saving account", "/v1/add-saving");

        infos.put("Endpoints", endpoints);



        return ResponseEntity.status(HttpStatus.ACCEPTED).body(infos);
    }
}
