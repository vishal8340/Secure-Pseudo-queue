package com.secure.controller;

import com.secure.exceptions.handler.AccountAlreadyExists;
import com.secure.exceptions.handler.AccountNotFound;
import com.secure.exceptions.handler.InvalidTransaction;
import com.secure.exceptions.handler.UnableToProcess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerHandlers {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleAllException(RuntimeException ex) {
        if(ex.getMessage().equals(new AccountAlreadyExists().getMessage())) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } else if(ex.getMessage().equals(new AccountNotFound().getMessage())) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } else if(ex.getMessage().equals(new UnableToProcess().getMessage())
                    || ex.getMessage().equals(new InvalidTransaction().getMessage())) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.GATEWAY_TIMEOUT);
    }
}