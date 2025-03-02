package com.nilsw13.web;


import com.nilsw13.dto.TransactionDto;
import com.nilsw13.model.Transaction;
import com.nilsw13.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }


    @GetMapping("/v1/transactions")
    public List<Transaction> getTransactions(){
        return transactionService.findAll();
    }

    @PostMapping("/v1/add-transaction")
    public ResponseEntity<Transaction> addTransaction(@RequestBody @Valid TransactionDto transactionDto){


            Transaction transaction = transactionService.create(
                    transactionDto.getAmount(),
                    transactionDto.getReference(),
                    transactionDto.getCustomer(),
                    transactionDto.getPaymentMethod(),
                    transactionDto.getType()
            );

            return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }



}

