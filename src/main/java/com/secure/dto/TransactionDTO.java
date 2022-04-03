package com.secure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
    private String accountHolderName;
    private String type;
    private String amount;
    private String currency;
    private String accountFrom;
}
