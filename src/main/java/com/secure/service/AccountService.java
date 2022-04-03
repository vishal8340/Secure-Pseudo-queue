package com.secure.service;

import com.secure.dto.AccountDTO;
import com.secure.entity.Account;

public interface AccountService {
    Account openAccount(AccountDTO accountDTO);
    Account closeAccount(AccountDTO accountDTO);
    boolean isValidAccount(Account account);
}
