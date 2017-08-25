package com.ethicost.transaction;

import com.ethicost.merchant.Merchant;
import com.ethicost.merchant.MerchantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class TransactionService {

    private TransactionRepository transactionRepository;

    private MerchantRepository merchantRepository;

    public List<Transaction> findTransactionsByUserId(int userId) {
        return transactionRepository.findTransactionsByUserId(userId);
    }
}
