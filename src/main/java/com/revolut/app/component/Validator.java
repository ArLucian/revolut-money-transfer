package com.revolut.app.component;

import java.util.Arrays;
import java.util.List;

import javax.money.CurrencyUnit;
import javax.money.Monetary;
import javax.money.UnknownCurrencyException;

import org.springframework.stereotype.Component;

import com.revolut.app.exceptions.custom.BusinessException;

@Component
public class Validator {

	public void validateCurrencies(String fromCurrency, String toCurrency) {
		
		List<String> invalidCurrencies = Arrays.asList("XTS", "XSU", "XDR", "XBD", "XBC", "XBB", "XBA", "USN", "MXV", "COU");

        try {
        	CurrencyUnit fromCurrencyUnit = Monetary.getCurrency(fromCurrency);
        	CurrencyUnit toCurrencyUnit = Monetary.getCurrency(toCurrency);
        } catch (UnknownCurrencyException e) {
            throw new BusinessException("Unknow currency!");
        }
        
        if(fromCurrency.equals("XXX") || toCurrency.equals("XXX")) {
        	throw new BusinessException("Cannot make exchange with no currency!");
        }
        if(invalidCurrencies.contains(fromCurrency) || invalidCurrencies.contains(toCurrency)) {
        	throw new BusinessException("Currencies are not acceptable for exchange !");
        }
	}
}
