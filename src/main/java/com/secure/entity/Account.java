package com.secure.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @Column(name = "account_number")
    private int accountNumber;
    @Column(name = "account_holder_name")
    private String accountHolderName;
    private double balance;
    private boolean status;
    private String currencyList;
}
