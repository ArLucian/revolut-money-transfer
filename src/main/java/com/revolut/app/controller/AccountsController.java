package com.revolut.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revolut.app.controller.component.ResponseCreator;
import com.revolut.app.controller.model.AccountModel;
import com.revolut.app.controller.model.AccountResponseModel;
import com.revolut.app.exceptions.custom.BusinessException;
import com.revolut.app.model.Account;
import com.revolut.app.service.AccountService;

@Controller
public class AccountsController {

    @Autowired
    AccountService accountService;

    @Autowired
    private ResponseCreator responseCreator;

    @GetMapping(value = "/accounts",
            produces = { "application/json" })
    public @ResponseBody List<Account> getAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping(value = "/accounts/{id}",
            produces = { "application/json" })
    public @ResponseBody Account getAccountById(@PathVariable Long id) {
        Optional<Account> requestdAccount = accountService.getAccountById(id);

        if(requestdAccount.isPresent()){
            return requestdAccount.get();
        }else{
            throw new BusinessException("Requested account for the id is not present!");
        }
    }

    @PostMapping(value = "/accounts",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AccountResponseModel> createAccount(@RequestBody AccountModel accountModel) {
        Account account = accountService.save(accountModel);
        return new ResponseEntity<>(responseCreator.createPostAccountsResponse(account), HttpStatus.ACCEPTED);
    }
    }
