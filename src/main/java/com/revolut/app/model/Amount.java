package com.revolut.app.model;

import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Amount {

    @Id
    private BigDecimal value;

    private Currency currency;

    private Amount(Builder builder) {
        this.value = builder.value;
        this.currency = builder.currency;
    }

    public Amount() {
    }

    public BigDecimal getValue() {
        return value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private BigDecimal value;
        private Currency currency;

        public Builder withValue(BigDecimal value) {
            this.value = value;
            return this;
        }

        public Builder withCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public Amount build() {
            return new Amount(this);
        }
    }

}
