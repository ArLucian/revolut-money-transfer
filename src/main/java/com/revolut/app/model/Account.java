package com.revolut.app.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "seq",
        initialValue = 3,
        allocationSize = 100)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "seq")
    private long id;
    private BigDecimal balance;
    private String currency;

    private Account(Builder builder) {
        this.id = builder.id;
        this.balance = builder.balance;
        this.currency = builder.currency;
    }

    public Account() {
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

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

        public Account build() {
            return new Account(this);
        }
    }

}
