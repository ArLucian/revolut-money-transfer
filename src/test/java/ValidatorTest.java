import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revolut.app.RevolutMoneyTransfer;
import com.revolut.app.component.Validator;
import com.revolut.app.exceptions.custom.BusinessException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RevolutMoneyTransfer.class)
public class ValidatorTest {
	
	@Autowired
	private Validator validator;
	
	@Test(expected= BusinessException.class)
	public void validateCurrencies_getException_OnInvalidCurrencies() {
		String fromCurrency = "XXX";
		String toCurrency = "EUR";
		
		validator.validateCurrencies(fromCurrency, toCurrency);
	}
	
	@Test(expected= BusinessException.class)
	public void validateCurrencies_getException_OnUnknownCurrencies() {
		String fromCurrency = "YXX";
		String toCurrency = "EUR";
		
		validator.validateCurrencies(fromCurrency, toCurrency);
		
	}

}
