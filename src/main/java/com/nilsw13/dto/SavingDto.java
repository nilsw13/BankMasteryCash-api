package com.nilsw13.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class SavingDto {

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private BigDecimal rate;

    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public @NotNull BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(@NotNull BigDecimal amount) {
        this.amount = amount;
    }

    public @NotNull BigDecimal getRate() {
        return rate;
    }

    public void setRate(@NotNull BigDecimal rate) {
        this.rate = rate;
    }


}
