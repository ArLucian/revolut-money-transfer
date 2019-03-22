package com.revolut.app.controller.model;

import java.math.BigDecimal;

public class TransactionResponseModel {

    private String responseText;
    private BigDecimal exchangedAmount;
    private String currency;
    private Long fromAccount;
    private Long toAccount;

    private TransactionResponseModel(Builder builder) {
        this.responseText = builder.responseText;
        this.exchangedAmount = builder.exchangedAmount;
        this.currency = builder.currency;
        this.fromAccount = builder.fromAccount;
        this.toAccount = builder.toAccount;
    }

    public TransactionResponseModel() {
    }

    public String getResponseText() {
        return responseText;
    }

    public BigDecimal getExchangedAmount() {
        return exchangedAmount;
    }

    public String getCurrency() {
        return currency;
    }

    public Long getFromAccount() {
        return fromAccount;
    }

    public Long getToAccount() {
        return toAccount;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String responseText;
        private BigDecimal exchangedAmount;
        private String currency;
        private Long fromAccount;
        private Long toAccount;

        public Builder withResponseText(String responseText) {
            this.responseText = responseText;
            return this;
        }

        public Builder withExchangedAmount(BigDecimal exchangedAmount) {
            this.exchangedAmount = exchangedAmount;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder withFromAccount(Long fromAccount) {
            this.fromAccount = fromAccount;
            return this;
        }

        public Builder withToAccount(Long toAccount) {
            this.toAccount = toAccount;
            return this;
        }

        public TransactionResponseModel build() {
            return new TransactionResponseModel(this);
        }
    }

}
