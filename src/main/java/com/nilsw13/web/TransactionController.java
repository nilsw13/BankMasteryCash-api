package com.nilsw13.web;


import com.nilsw13.model.Transaction;
import com.nilsw13.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping("/transactions")
    public List<Transaction> getTransactions(){
        return transactionService.findAll();
    }

}
