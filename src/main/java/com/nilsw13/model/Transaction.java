package com.nilsw13.model;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {

    UUID id;
    String reference;
    String paymentMethod;



    String customer;
    BigDecimal amount;
    LocalDateTime created_at;

    public Transaction() {
    }

    public Transaction( String reference, String customer, String paymentMethod, BigDecimal amount) {
        this.id = UUID.randomUUID();
        this.reference = reference;
        this.customer = customer;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.created_at = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
