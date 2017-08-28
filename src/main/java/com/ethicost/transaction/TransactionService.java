package com.ethicost.transaction;

import com.ethicost.merchant.Merchant;
import com.ethicost.merchant.MerchantMapper;
import com.ethicost.merchant.MerchantRepository;
import com.ethicost.merchant.MerchantTransactionResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class TransactionService {

    private TransactionRepository transactionRepository;

    private MerchantRepository merchantRepository;

    public List<Transaction> findAllTransactions() {
        Iterable<Transaction> transactions = transactionRepository.findAll();
        List<Transaction> transactionList = new ArrayList<>();
        transactions.forEach(transactionList::add);
        return transactionList;
    }

    public UserTransactionsResponse getEthicalScoreTransactions(List<String> accountIds) {
        List<Merchant> merchants = merchantRepository.findMerchantsByAccountList(accountIds);
        List<MerchantTransactionResponse> merchantResponses = merchants.stream().map(MerchantMapper::map).collect(Collectors.toList());
        UserTransactionsResponse userTransactionsResponse = new UserTransactionsResponse();
        userTransactionsResponse.setMerchantResponses(merchantResponses);
        return userTransactionsResponse;
    }
}
