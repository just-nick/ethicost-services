package com.ethicost.merchant;

import com.ethicost.transaction.TransactionResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MerchantTransactionResponse {

    private int id;

    private String merchantName;

    private String merchantDescription;

    private Integer rating;

    private String category;

    private List<TransactionResponse> transactionResponses;

    private BigDecimal totalSpend;
}
