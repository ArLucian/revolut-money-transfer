package com.revolut.app.controller.model.mapper;

import org.springframework.stereotype.Component;

import com.revolut.app.controller.model.AccountModel;
import com.revolut.app.model.Account;

@Component
public class AccountMapper {

    public Account mapAccountEntityToModel(AccountModel account) {
        return new Account.Builder().withId(account.getId())
                .withBalance(account.getBalance())
                .withCurrency(account.getCurrency())
                .build();
    }
}
