package com.nilsw13.service;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Profile("dev")
@Service
public class DbInitializer {

    private final TransactionService transactionService;


    public DbInitializer(TransactionService transactionService) {
        this.transactionService = transactionService;
    }



    @PostConstruct
    public void init() {
        System.out.println("initiliazing DB in dev env");
        transactionService.create( BigDecimal.valueOf(19.99), "Netflix", "nilsw13", "bank_transfer" );
        transactionService.create( BigDecimal.valueOf(250), "Leclerc", "nilsw13", "credit_card" );
        transactionService.create( BigDecimal.valueOf(12.70), "JetBrains", "nilsw13", "credit_card" );
        transactionService.create( BigDecimal.valueOf(12), "Tobacco", "nilsw13", "credit_card" );
        transactionService.create( BigDecimal.valueOf(25), "Fitness Park", "nilsw13", "credit_card" );
        transactionService.create( BigDecimal.valueOf(620), "Rent", "nilsw13", "bank_transfer" );
    }
}


