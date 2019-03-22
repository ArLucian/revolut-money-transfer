import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.revolut.app.RevolutMoneyTransfer;
import com.revolut.app.model.Account;
import com.revolut.app.repository.AccountRepository;
import com.revolut.app.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RevolutMoneyTransfer.class)
public class AccountTest {

    @MockBean
    private AccountRepository accountRepository;
    
    @Autowired
    private AccountService accountService;
    
    Optional<Account> expectedAccount;
    
    @Before
    public void init() {
    	MockitoAnnotations.initMocks(this);
    	expectedAccount = Optional.of(new Account.Builder().withId(3).withBalance(BigDecimal.valueOf(10000)).withCurrency("USD").build());
    }
    
    @Test
    public void getAccountById_FetchEntityById_AsExpected(){
    	when(accountRepository.findById(Long.valueOf(3))).thenReturn(expectedAccount);
    	
    	Optional<Account> actualAccount = accountService.getAccountById(Long.valueOf(3));
    	
    	assertNotNull(actualAccount);
    	
    	assertEquals(expectedAccount.get().getId(), actualAccount.get().getId());
    	assertEquals(expectedAccount.get().getBalance(), actualAccount.get().getBalance());
    	assertEquals(expectedAccount.get().getCurrency(), actualAccount.get().getCurrency());
    }
    
}
