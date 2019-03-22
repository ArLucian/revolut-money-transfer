package com.revolut.app.controller.component;

import static com.revolut.app.Constants.CREATE_ACCOUNT_MESSAGE;

import org.springframework.stereotype.Component;

import com.revolut.app.controller.model.AccountResponseModel;
import com.revolut.app.controller.model.TransactionResponseModel;
import com.revolut.app.model.Account;
import com.revolut.app.model.Transaction;

@Component
public class ResponseCreator {

    public AccountResponseModel createPostAccountsResponse(Account account) {
        return new AccountResponseModel.Builder().withText(CREATE_ACCOUNT_MESSAGE)
                .withId(account.getId())
                .withBalance(account.getBalance())
                .withCurrency(account.getCurrency())
                .build();
    }

    public TransactionResponseModel createPostTransactionsResponse(Transaction executedTransaction) {
        return new TransactionResponseModel.Builder().withResponseText("Transaction successful !")
                .withExchangedAmount(executedTransaction.getAmount())
                .withCurrency(executedTransaction.getCurrency())
                .withFromAccount(executedTransaction.getFromAccount())
                .withToAccount(executedTransaction.getToAccount())
                .build();
    }
}
