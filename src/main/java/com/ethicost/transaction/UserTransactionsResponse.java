package com.ethicost.transaction;

import com.ethicost.merchant.MerchantTransactionResponse;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserTransactionsResponse {
    private List<MerchantTransactionResponse> merchantResponses;
}
