package com.nilsw13.web;

import com.nilsw13.dto.SavingDto;
import com.nilsw13.model.SavingAccount;
import com.nilsw13.service.SavingAccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SavingAccountsController {

    private final SavingAccountService savingAccountService;

    public SavingAccountsController(SavingAccountService savingAccountService) {
        this.savingAccountService = savingAccountService;
    }

    @GetMapping("/v1/savings")
    public ResponseEntity<List<SavingAccount>> getSavings(){

        List<SavingAccount> accountList = savingAccountService.findAll();

        return ResponseEntity.ok().body(accountList);
    }

    @PostMapping("/v1/add-saving")
    public ResponseEntity<SavingAccount> addSavingAccount(@RequestBody SavingDto savingDto) {
        SavingAccount savingAccount = savingAccountService.create(
               savingDto.getName(),
                    savingDto.getAmount(),
                        savingDto.getRate()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(savingAccount);

    }

}
