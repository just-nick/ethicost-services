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

    private String accountId;

    private String merchant;

    private String description;

    private String transactionId;

    private String amount;

    private String runningBalance;

    private String currencyCode;

    private String debit;

    private String transactionDate;

    private String effectiveDate;

    private String latitude;

    private String longitude;

    private String category;
}
