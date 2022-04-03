package com.secure.exceptions.handler;

public class UnableToProcess extends RuntimeException {
    public UnableToProcess(){
        super("Oops! Unable to process!!!");
    }
}
