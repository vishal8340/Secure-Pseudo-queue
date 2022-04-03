package com.secure.exceptions.handler;

public class AccountAlreadyExists extends RuntimeException {
    public AccountAlreadyExists() {
        super("Oops! Account already exists!!");
    }
}
