import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.revolut.app.Constants.TransactionStatus;
import com.revolut.app.RevolutMoneyTransfer;
import com.revolut.app.model.Transaction;
import com.revolut.app.repository.TransactionRepository;
import com.revolut.app.service.TransactionService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RevolutMoneyTransfer.class)
public class TransactionTest {

    @MockBean
    private TransactionRepository transactionRepository;
    
    @Autowired
    private TransactionService transactionService;
    
    List<Transaction> expectedTransactions = new ArrayList<>();
    
    @Before
    public void init() {
    	MockitoAnnotations.initMocks(this);
    	Transaction expectedTransaction = new Transaction.Builder().withAmount(BigDecimal.valueOf(1000)).withId(Long.valueOf(3)).withCurrency("EUR")
    			.withFromAccount(1).withToAccount(2).withStatus(TransactionStatus.FINISHED).build();
    	expectedTransactions.add(expectedTransaction);
    }
    
    @Test
    public void getTransactionById_FetchEntity_AsExpected() {
    	when(transactionRepository.findAll()).thenReturn(expectedTransactions);
    	List<Transaction> actualTransactions = transactionService.getTransactions();
    	
    	assertNotNull(actualTransactions);
    	assertEquals(actualTransactions.size(), expectedTransactions.size());
    }
}
