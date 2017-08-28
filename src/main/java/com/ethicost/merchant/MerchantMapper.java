package com.ethicost.merchant;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class MerchantMapper {

    public static MerchantTransactionResponse map(Merchant merchant) {
        MerchantTransactionResponse merchantTransactionResponse = new MerchantTransactionResponse();
        BeanUtils.copyProperties(merchant, merchantTransactionResponse);
        return merchantTransactionResponse;
    }
}
