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
    private String accountId;
    private String transactionId;
    private BigDecimal amount;
    private String description;
    private Boolean debit;
    private String category;
    private String runningBalance;
    private String currencyCode;
    private String merchant;
    private String latitude;
    private String longitude;
}
