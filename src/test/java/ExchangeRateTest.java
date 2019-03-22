import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.CurrencyConversionException;
import javax.money.convert.MonetaryConversions;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revolut.app.RevolutMoneyTransfer;
import com.revolut.app.exceptions.custom.BusinessException;
import com.revolut.app.service.ExchangeRateService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RevolutMoneyTransfer.class)
public class ExchangeRateTest {
	
	@Autowired
	private ExchangeRateService exchangeRateService;
	
	String fromCurrency = "USD";
	String toCurrency = "EUR";
	
	BigDecimal expectedExchangeRateValue;
	
	@Before
	public void init() {
		
		MonetaryAmount oneFromCurrencyUnit = Monetary.getDefaultAmountFactory()
                .setCurrency(fromCurrency)
                .setNumber(1)
                .create();

        MonetaryAmount convertedAmount= null;

        try {
        	CurrencyConversion conversion = MonetaryConversions.getConversion(toCurrency);
            convertedAmount = oneFromCurrencyUnit.with(conversion);
        } catch (CurrencyConversionException conversionException) {
            throw new BusinessException("Failed to convert currency");
        }

        expectedExchangeRateValue = BigDecimal.valueOf(convertedAmount.getNumber()
                .doubleValue());
	}
	
    @Test
    public void givenAmount_whenConversion_thenNotNull() {
		String fromCurrency = "USD";
		String toCurrency = "EUR";
		
		BigDecimal exchangeRateAmountTest = exchangeRateService.exchangeAmount(fromCurrency, toCurrency);
		
		assertNotNull(exchangeRateAmountTest);
    }
    
    @Test
    public void givenAmount_whenConversion_ConvertAmount() {
		String fromCurrency = "USD";
		String toCurrency = "EUR";
		
		BigDecimal actualExchangeRateAmount = exchangeRateService.exchangeAmount(fromCurrency, toCurrency);
		
		assertEquals(expectedExchangeRateValue, actualExchangeRateAmount);
    }
}
