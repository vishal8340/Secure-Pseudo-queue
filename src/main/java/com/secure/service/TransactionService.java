package com.secure.service;

import com.secure.dto.TransactionDTO;
import com.secure.entity.Transaction;

public interface TransactionService {
    Transaction creditBalance(Transaction transaction);
    Transaction debitBalance(Transaction transaction);
    Transaction checkBalance(Transaction transaction);
    Transaction doTransaction(TransactionDTO transactionDTO);
}
