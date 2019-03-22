package com.revolut.app.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revolut.app.Constants.TransactionStatus;
import com.revolut.app.exceptions.custom.BusinessException;
import com.revolut.app.model.Account;
import com.revolut.app.model.Transaction;
import com.revolut.app.repository.AccountRepository;
import com.revolut.app.repository.TransactionRepository;

@Service
public class TransactionsService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ExchangeRateService exchangeRateService;

    public Transaction executeTransaction(Transaction transaction) {

        try {
            makeTransaction(transaction);
            transaction.setStatus(TransactionStatus.FINISHED);
            return transactionRepository.save(transaction);
        } catch (Exception e) {
            transaction.setStatus(TransactionStatus.FAILED);
            transactionRepository.save(transaction);
            throw new BusinessException("Transaction returned as failed! " + e.getMessage());
        }
    }

    private void makeTransaction(Transaction transaction) {

        Account fromAccountEntity = getAccount(transaction.getFromAccount());
        Account toAccountEntity = getAccount(transaction.getToAccount());

        if (fromAccountEntity.getCurrency()
                .equals(toAccountEntity.getCurrency())) {
            if (fromAccountEntity.getCurrency()
                    .equals(transaction.getCurrency())) {
                updateFromAccount(toAccountEntity, transaction.getAmount());
                updateToAccount(toAccountEntity, transaction.getAmount());
            } else {
                updateFromAccount(fromAccountEntity, calculateExchangedAmountConverted(transaction, fromAccountEntity));
                updateToAccount(toAccountEntity, calculateExchangedAmountConverted(transaction, toAccountEntity));
            }
        } else {
            if (fromAccountEntity.getCurrency()
                    .equals(transaction.getCurrency())) {
                updateFromAccount(toAccountEntity, transaction.getAmount());
            } else {
                updateFromAccount(fromAccountEntity, calculateExchangedAmountConverted(transaction, fromAccountEntity));
            }
            if (toAccountEntity.getCurrency()
                    .equals(transaction.getCurrency())) {
                updateToAccount(toAccountEntity, transaction.getAmount());
            } else {
                updateToAccount(toAccountEntity, calculateExchangedAmountConverted(transaction, toAccountEntity));
            }
        }
        saveAccounts(fromAccountEntity, toAccountEntity);
    }

    private BigDecimal calculateExchangedAmountConverted(Transaction transaction, Account toAccountEntity) {
        return transaction.getAmount()
                .multiply(exchangeRateService.exchangeAmount(transaction.getCurrency(), toAccountEntity.getCurrency()));
    }

    private void updateToAccount(Account toAccountEntity, BigDecimal amount) {
        toAccountEntity.setBalance(toAccountEntity.getBalance()
                .add(amount));
    }

    private void updateFromAccount(Account fromAccountEntity, BigDecimal amount) {
        if (fromAccountEntity.getBalance()
                .compareTo(amount) < 0) {
            throw new BusinessException("Account does not have the amount of money to transfer!");
        } else {
            fromAccountEntity.setBalance(fromAccountEntity.getBalance()
                    .subtract(amount));
        }
    }

    private void saveAccounts(Account fromAccountEntity, Account toAccountEntity) {
        accountRepository.save(fromAccountEntity);
        accountRepository.save(toAccountEntity);
    }

    private Account getAccount(long id) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            return account.get();
        } else {
            throw new BusinessException("Account is not present!");
        }
    }

    public List<Transaction> getTransactionById() {
        return transactionRepository.findAll();
    }

}
