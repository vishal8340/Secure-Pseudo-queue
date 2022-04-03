/**
 * @author Vishal Kumar - 2022
 */

package com.secure.service;


import com.secure.dto.AccountDTO;
import com.secure.entity.Account;
import com.secure.entity.User;
import com.secure.exceptions.handler.UnableToProcess;
import com.secure.exceptions.handler.AccountAlreadyExists;
import com.secure.exceptions.handler.AccountNotFound;
import com.secure.mapper.Convertor;
import com.secure.repository.AccountRepository;
import com.secure.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.Boolean.TRUE;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    Convertor convertor;
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Account openAccount(AccountDTO accountDTO) {
        Account account = getAccount(accountDTO);
        userRepository.findByName(account.getAccountHolderName())
                .ifPresent(error -> {
                    throw new AccountAlreadyExists();
                });

        int accountNumber = getAccountNumber();
        checkAccountExists(accountNumber);
        setBasicDetailsForNewAccount(account, accountNumber);

        User user = new User();
        user.setName(account.getAccountHolderName());
        userRepository.save(user);
        return accountRepository.save(account);
    }

    private void setBasicDetailsForNewAccount(Account account, int accountNumber) {
        account.setAccountNumber(accountNumber);
        account.setStatus(TRUE);
        account.setCurrencyList("INR");
    }

    private void checkAccountExists(int accountNumber) {
        accountRepository.findByAccountNumber(accountNumber)
                .ifPresent(error -> {
                    throw new AccountAlreadyExists();
                });
    }

    private int getAccountNumber() {
        return (int) (Math.random() * (100000000 - 999999)) + 999999;
    }


    private Account getAccount(AccountDTO accountDTO) {
        return convertor.mapBy(accountDTO);
    }

    @Override
    public Account closeAccount(AccountDTO accountDTO) {
        Account account = getAccount(accountDTO);
        Account account1 = checkForAccountNotFound(account.getAccountHolderName());
        account1.setStatus(Boolean.FALSE);
        return accountRepository.save(account1);
    }

    @Override
    public boolean isValidAccount(Account account) {
        Optional<Account> accountOptional = accountRepository
                .findByAccountHolderName(account.getAccountHolderName());

        if (accountOptional.isPresent()) {
            return accountOptional.get().isStatus();
        }
        return false;
    }

    public Account checkBalance(String accountHolderName) {
        return checkForAccountNotFound(accountHolderName);
    }

    public Account debitBalance(Account account) {
        Account account1 = checkForAccountNotFound(account.getAccountHolderName());
        if (account1.getBalance() >= account.getBalance()) {
            account1.setBalance(account1.getBalance() - account.getBalance());
            accountRepository.save(account1);
        } else {
            throw new UnableToProcess();
        }
        return account1;
    }

    public Account creditBalance(Account account) {
        Account account1 = checkForAccountNotFound(account.getAccountHolderName());
        account1.setBalance(account1.getBalance() + account.getBalance());
        accountRepository.save(account1);
        return account1;
    }

    private Account checkForAccountNotFound(String account) {
        return accountRepository.findByAccountHolderName(account)
                .orElseThrow(AccountNotFound::new);
    }
}
