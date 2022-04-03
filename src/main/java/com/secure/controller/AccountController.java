/**
 *
 */

package com.secure.controller;

import com.secure.dto.AccountDTO;
import com.secure.entity.Account;
import com.secure.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    AccountServiceImpl accountService;

    @PostMapping(value = "/api/account")
    public ResponseEntity<Account> openAccount(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.openAccount(accountDTO), HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/account")
    public ResponseEntity<Account> closeAccount(@RequestBody AccountDTO accountDTO) {
        return new ResponseEntity<>(accountService.closeAccount(accountDTO), HttpStatus.OK);
    }
}
