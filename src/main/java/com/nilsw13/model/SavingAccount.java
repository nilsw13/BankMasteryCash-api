package com.nilsw13.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SavingAccount {

    String name, iconRef;

    BigDecimal amount, rate;

    LocalDateTime created_At;

    public SavingAccount() {
    }

    public SavingAccount(String name, String iconRef, BigDecimal amount, BigDecimal rate) {
        this.name = name;
        this.iconRef = iconRef;
        this.amount = amount;
        this.rate = rate;
        this.created_At = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconRef() {
        return iconRef;
    }

    public void setIconRef(String iconRef) {
        this.iconRef = iconRef;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public LocalDateTime getCreated_At() {
        return created_At;
    }

    public void setCreated_At(LocalDateTime created_At) {
        this.created_At = created_At;
    }
}
