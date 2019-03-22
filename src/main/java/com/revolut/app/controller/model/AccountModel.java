package com.revolut.app.controller.model;

import java.math.BigDecimal;

public class AccountModel {

    private long id;
    private BigDecimal balance;
    private String currency;

    private AccountModel(Builder builder) {
        this.id = builder.id;
        this.balance = builder.balance;
        this.currency = builder.currency;
    }

    public AccountModel() {
    }

    public long getId() {
        return id;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getCurrency() {
        return currency;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private long id;
        private BigDecimal balance;
        private String currency;

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public AccountModel build() {
            return new AccountModel(this);
        }
    }
}
