package com.secure.mapper;

import com.secure.dto.AccountDTO;
import com.secure.dto.TransactionDTO;
import com.secure.entity.Account;
import com.secure.entity.Transaction;
import org.springframework.stereotype.Component;

@Component
public class Convertor {
    public static Account mapBy(AccountDTO accountDTO){
        Account account = new Account();
        account.setAccountHolderName(accountDTO.getAccountHolderName());
        account.setBalance(Double.parseDouble(accountDTO.getBalance()));
        return account;
    }

    public static Transaction mapBy(TransactionDTO transactionDTO){
        Transaction transaction = new Transaction();
        Account account = new Account();
        account.setAccountHolderName(transactionDTO.getAccountHolderName());
        account.setBalance(Double.parseDouble(transactionDTO.getAmount()));
        transaction.setAmount(Double.parseDouble(transactionDTO.getAmount()));
        transaction.setAccountFrom(Long.parseLong(transactionDTO.getAccountFrom()));
        transaction.setType(transactionDTO.getType());
        transaction.setCurrency(transactionDTO.getCurrency());
        transaction.setAccount(account);
        return transaction;
    }

}
