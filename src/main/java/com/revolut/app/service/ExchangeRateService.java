package com.revolut.app.service;

import java.math.BigDecimal;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.CurrencyConversionException;
import javax.money.convert.MonetaryConversions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.revolut.app.exceptions.custom.BusinessException;

@Service
public class ExchangeRateService {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(ExchangeRateService.class);

    public BigDecimal exchangeAmount(String fromCurrency, String toCurrency) {
    	
        MonetaryAmount oneFromCurrencyUnit = Monetary.getDefaultAmountFactory()
                .setCurrency(fromCurrency)
                .setNumber(1)
                .create();

        CurrencyConversion conversion = null;
        MonetaryAmount convertedAmount= null;

        try {
            conversion = MonetaryConversions.getConversion(toCurrency);
            convertedAmount = oneFromCurrencyUnit.with(conversion);
        } catch (CurrencyConversionException conversionException) {
            LOGGER.warn("All delegate prov iders failed to deliver rate!");
            throw new BusinessException("Failed to convert currency");
        }

        return BigDecimal.valueOf(convertedAmount.getNumber()
                .doubleValue());
    }
}
