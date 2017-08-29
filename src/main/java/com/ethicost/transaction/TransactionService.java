package com.ethicost.transaction;

import com.ethicost.account.MacAccountResponse;
import com.ethicost.merchant.Merchant;
import com.ethicost.merchant.MerchantMapper;
import com.ethicost.merchant.MerchantRepository;
import com.ethicost.merchant.MerchantTransactionResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class TransactionService {
    private static final String CLIENT_ID = "client_id";
    private static final String AUTHORIZATION = "Authorization";

    private TransactionRepository transactionRepository;

    private MerchantRepository merchantRepository;

    private final Environment environment;

    public List<Transaction> findAllTransactions() {
        Iterable<Transaction> transactions = transactionRepository.findAll();
        List<Transaction> transactionList = new ArrayList<>();
        transactions.forEach(transactionList::add);
        return transactionList;
    }

    public List<Transaction> findAllTransactions(List<String> accountIds) {
        return transactionRepository.findAllByAccountIdIn(accountIds);
    }

    public UserTransactionsResponse getEthicalScoreTransactions(List<String> accountIds) {
        List<Merchant> merchants = merchantRepository.findMerchantsByAccountList(accountIds);
        List<MerchantTransactionResponse> merchantResponses = merchants.stream().map(MerchantMapper::map).collect(Collectors.toList());
        UserTransactionsResponse userTransactionsResponse = new UserTransactionsResponse();
        userTransactionsResponse.setMerchantResponses(merchantResponses);
        return userTransactionsResponse;
    }
}
