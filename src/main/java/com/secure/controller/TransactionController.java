package com.secure.controller;

import com.secure.dto.TransactionDTO;
import com.secure.entity.Transaction;
import com.secure.service.TransactionService;
import com.secure.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    TransactionServiceImpl transactionService;

    @PostMapping(value = "/api/transaction")
    public ResponseEntity<Transaction> doTransaction(@RequestBody TransactionDTO transactionDTO) {
        return new ResponseEntity<Transaction>(transactionService.doTransaction(transactionDTO), HttpStatus.OK);
    }
}
