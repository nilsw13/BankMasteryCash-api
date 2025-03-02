package com.nilsw13.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public class TransactionDto {

    @NotNull
    BigDecimal amount;

    @NotBlank
    @Size(min= 1, max = 255)
    String reference;

    @NotBlank
    @Size(min=1 , max = 255)
    String customer;

    @NotBlank
    @Size(min = 1, max = 255)
    String paymentMethod;

    @NotBlank
    String type;

    public @NotBlank String getType() {
        return type;
    }

    public void setType(@NotBlank String type) {
        this.type = type;
    }

    public @NotBlank @Size(min = 1, max = 255) String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(@NotBlank @Size(min = 1, max = 255) String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public @NotNull BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(@NotNull BigDecimal amount) {
        this.amount = amount;
    }

    public @NotBlank @Size(min = 1, max = 255) String getReference() {
        return reference;
    }

    public void setReference(@NotBlank @Size(min = 1, max = 255) String reference) {
        this.reference = reference;
    }

    public @NotBlank @Size(min = 1, max = 255) String getCustomer() {
        return customer;
    }

    public void setCustomer(@NotBlank @Size(min = 1, max = 255) String customer) {
        this.customer = customer;
    }
}


