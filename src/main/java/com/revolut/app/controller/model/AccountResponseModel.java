package com.revolut.app.controller.model;

import java.math.BigDecimal;

public class AccountResponseModel {

    private String text;
    private long id;
    private BigDecimal balance;
    private String currency;

    private AccountResponseModel(Builder builder) {
        this.text = builder.text;
        this.id = builder.id;
        this.balance = builder.balance;
        this.currency = builder.currency;
    }

    public AccountResponseModel() {
    }

    public String getText() {
        return text;
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
        private String text;
        private long id;
        private BigDecimal balance;
        private String currency;

        public Builder withText(String text) {
            this.text = text;
            return this;
        }

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

        public AccountResponseModel build() {
            return new AccountResponseModel(this);
        }
    }

}
