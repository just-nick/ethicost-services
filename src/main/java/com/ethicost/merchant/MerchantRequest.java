package com.ethicost.merchant;

import com.ethicost.transaction.TransactionResponse;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class MerchantRequest {

    private String merchantName;

    private String merchantDescription;

    private Integer rating;

    private String category;
}
