package com.secure.service;

import com.secure.dto.TransactionDTO;
import com.secure.entity.Account;
import com.secure.entity.Transaction;
import com.secure.enums.TransactionType;
import com.secure.exceptions.handler.InvalidTransaction;
import com.secure.mapper.Convertor;
import com.secure.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService{
    @Autowired
    Convertor convertor;

    @Autowired
    AccountServiceImpl accountServiceImpl;

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction creditBalance(Transaction transaction) {
        Account account = accountServiceImpl.creditBalance(transaction.getAccount());
        transaction.setAccount(account);
        int transactionId = (int) (Math.random()*(100000000 - 999999)) + 999999;
        transaction.setTransactionId(transactionId);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction debitBalance(Transaction transaction) {
        Account account = accountServiceImpl.debitBalance(transaction.getAccount());
        transaction.setAccount(account);
        int transactionId = (int) (Math.random()*(100000000 - 999999)) + 999999;
        transaction.setTransactionId(transactionId);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction checkBalance(Transaction transaction) {
        Account account = accountServiceImpl.checkBalance(transaction.getAccount().getAccountHolderName());
        transaction.setAccount(account);
        transaction.setAccountFrom(0);
        transaction.setAmount(0);
        int transactionId = (int) (Math.random()*(100000000 - 999999)) + 999999;
        transaction.setTransactionId(transactionId);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction doTransaction(TransactionDTO transactionDTO) {
        Transaction transaction = convertor.mapBy(transactionDTO);

        if(!isValidAccount(transaction.getAccount())){
            throw new InvalidTransaction();
        }

        TransactionType txnType = TransactionType.valueOf(transaction.getType());
        switch (txnType) {
            case CREDIT:
                return creditBalance(transaction);
            case DEBIT:
                return debitBalance(transaction);
            case ENQUIRY:
                return checkBalance(transaction);
            default:
                throw new InvalidTransaction();
        }
    }

    private boolean isValidAccount(Account account) {
        return accountServiceImpl.isValidAccount(account);
    }
}
