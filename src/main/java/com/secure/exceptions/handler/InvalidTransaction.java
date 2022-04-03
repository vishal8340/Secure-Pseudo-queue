package com.secure.exceptions.handler;

public class InvalidTransaction extends  RuntimeException{
    public InvalidTransaction(){
        super("Invalid Transaction!!!");
    }
}
