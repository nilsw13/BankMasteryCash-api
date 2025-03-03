package com.nilsw13.service;

import com.nilsw13.model.SavingAccount;
import com.nilsw13.model.Transaction;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Profile("dev")
@Service
@DependsOn("dataSourceInitializer")
public class DbInitializer {

    private final TransactionService transactionService;
    private final SavingAccountService savingAccountService;


    public DbInitializer(TransactionService transactionService, SavingAccountService savingAccountService) {
        this.transactionService = transactionService;
        this.savingAccountService = savingAccountService;
    }



    @PostConstruct
    public void init() {
        System.out.println("initiliazing DB in dev env");
        List<Transaction> transactionList = transactionService.findAll();
        if (transactionList.isEmpty()) {
            transactionService.create( BigDecimal.valueOf(19.99), "Netflix", "nilsw13", "bank_transfer", "outcome" );
            transactionService.create( BigDecimal.valueOf(1250), "Web development", "nilsw13", "bank_transfer", "income" );
            transactionService.create( BigDecimal.valueOf(12.70), "JetBrains", "nilsw13", "credit_card", "outcome" );
            transactionService.create( BigDecimal.valueOf(12), "Tobacco", "nilsw13", "credit_card", "outcome" );
            transactionService.create( BigDecimal.valueOf(2500), "Salary", "nilsw13", "credit_card", "income" );
            transactionService.create( BigDecimal.valueOf(620), "Rent", "nilsw13", "bank_transfer", "outcome" );
        } else {
            System.out.println("Transaction table is already filled");
        }

        List<SavingAccount> accountList = savingAccountService.findAll();
        if(accountList.isEmpty()) {

            savingAccountService.create("Green Investment", BigDecimal.valueOf(250), BigDecimal.valueOf(0.75), "<TreePine/>");
            savingAccountService.create("Daily Savings", BigDecimal.valueOf(3600), BigDecimal.valueOf(0.75), "<PiggyBank/>");
            savingAccountService.create("Tech Assets", BigDecimal.valueOf(12500), BigDecimal.valueOf(0.75), "<ComputerIcon/>");
            savingAccountService.create("Real Estate", BigDecimal.valueOf(54320), BigDecimal.valueOf(0.75), "<RealEstate/>");
        } else {
            System.out.println("SavingAccount table is already filled");
        }

        
    }
}


