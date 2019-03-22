package com.revolut.app.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.revolut.app.controller.model.TransactionResponseModel;
import com.revolut.app.exceptions.custom.BusinessException;
import com.revolut.app.model.Transaction;
import com.revolut.app.service.TransactionService;

@Controller
public class TransactionsController {
	
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionsController.class);

    @Autowired
    private TransactionService transactionsService;

    @Autowired
    private ResponseCreator responseCreator;

    @GetMapping(value = "/transactions",
            produces = { "application/json" })
    public @ResponseBody List<Transaction> getTransactions() {
        return transactionsService.getTransactions();
    }

    @GetMapping(value = "/transactions/{id}",
            produces = { "application/json" })
    public @ResponseBody Transaction getTransactionsbyId(@PathVariable Long id) {
        Optional<Transaction> requestedTransaction = transactionsService.getTransactionsById(id);
        if (requestedTransaction.isPresent()) {
            return requestedTransaction.get();
        } else {
            throw new BusinessException("Requested transaction for the id is not present!");
        }

    }

    @PostMapping(value = "/transactions",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TransactionResponseModel> createTransaction(@RequestBody Transaction transaction) {
        Transaction executedTransaction = transactionsService.executeTransaction(transaction);
        TransactionResponseModel responseModel = responseCreator.createPostTransactionsResponse(executedTransaction);
        LOGGER.info("Transaction successful");
        return new ResponseEntity<>(responseModel, HttpStatus.ACCEPTED);
    }
}
