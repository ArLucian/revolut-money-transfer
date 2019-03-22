package com.revolut.app.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import com.revolut.app.Constants.TransactionStatus;

@Entity
@SequenceGenerator(name = "seq2",
        initialValue = 1,
        allocationSize = 100)
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "seq2")
    private long id;
    private long fromAccount;
    private long toAccount;
    private BigDecimal amount;
    private String currency;
    private TransactionStatus status;


    private Transaction(Builder builder) {
        this.id = builder.id;
        this.fromAccount = builder.fromAccount;
        this.toAccount = builder.toAccount;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.status = builder.status;
    }
    public Transaction() {
    }

    public long getId() {
        return id;
    }
    public long getFromAccount() {
        return fromAccount;
    }
    public long getToAccount() {
        return toAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrency() {
        return currency;
    }
    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public static Builder builder() {
        return new Builder();
    }
    public static final class Builder {
        private long id;
        private long fromAccount;
        private long toAccount;
        private BigDecimal amount;
        private String currency;
        private TransactionStatus status;

        public Builder withId(long id) {
            this.id = id;
            return this;
        }

        public Builder withFromAccount(long fromAccount) {
            this.fromAccount = fromAccount;
            return this;
        }

        public Builder withToAccount(long toAccount) {
            this.toAccount = toAccount;
            return this;
        }

        public Builder withAmount(BigDecimal amount) {
            this.amount = amount;
            return this;
        }

        public Builder withCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder withStatus(TransactionStatus status) {
            this.status = status;
            return this;
        }

        public Transaction build() {
            return new Transaction(this);
        }
    }


}