package com.revolut.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revolut.app.controller.model.AccountModel;
import com.revolut.app.controller.model.mapper.AccountMapper;
import com.revolut.app.model.Account;
import com.revolut.app.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper accountMapper;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account save(AccountModel account) {
        return accountRepository.save(accountMapper.mapAccountEntityToModel(account));
    }

    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

}
