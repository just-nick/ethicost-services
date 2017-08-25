package com.ethicost.transaction;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class TransactionResponse {

    private String transactionId;

    private BigDecimal amount;

    private String description;

    private String type;

    private Boolean debit;

    private String category;
}
