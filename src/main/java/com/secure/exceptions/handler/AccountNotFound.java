package com.secure.exceptions.handler;

public class AccountNotFound extends RuntimeException{
    public AccountNotFound() {
        super("Oops! No Account Found");
    }
}
