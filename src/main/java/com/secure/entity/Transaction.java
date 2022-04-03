package com.secure.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Transaction {
    @Id
    private int transactionId;
    private String type;
    private double amount;
    private String currency;
    private long accountFrom;
    @ManyToOne
    private Account account;
}
