package com.ethicost.account;

import com.ethicost.oauth.OAuthService;
import com.ethicost.oauth.OAuthToken;
import com.ethicost.transaction.Transaction;
import com.ethicost.transaction.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AccountService {

    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_SECRET = "client_secret";
    private static final String GRANT_TYPE = "grant_type";
    private static final String CODE = "code";
    private static final String AUTHORIZATION = "Authorization";

    private final Environment environment;

    @Autowired
    public AccountService(Environment environment) {
        this.environment = environment;
    }

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private OAuthService oAuthService;

    @Transactional
    public List<String> getAccounts(String authorizationHeader) {

        String baseApiUrl = environment.getProperty("macquarie.apiUrl");
        String clientId = environment.getProperty("macquarie.oauth.clientId");
        String accountPath = environment.getProperty("macquarie.api.accountPath");
        RestTemplate restTemplate = new RestTemplate();
        String apiPath = baseApiUrl + accountPath;

        HttpHeaders headers = new HttpHeaders();
        //headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set(CLIENT_ID, clientId);

        String refreshToken = oAuthService.refreshToken(authorizationHeader);
        if (null == refreshToken) {
            headers.set(AUTHORIZATION, authorizationHeader);
        } else {
            headers.set(AUTHORIZATION, refreshToken);
        }

        HttpEntity<String> entity = new HttpEntity<String>("", headers);
        List<String> accountIds = restTemplate.exchange(apiPath, HttpMethod.GET, entity, MacAccountResponse.class)
            .getBody().getAccounts().stream()
                .map(Account::getAccount_id)
                .collect(Collectors.toList());

        transactionRepository.deleteAllByAccountIdIn(accountIds);
        List<Transaction> transactions = new ArrayList<>();

        accountIds.forEach((accountId) -> {
            HttpEntity<String> entity2 = new HttpEntity<String>("", headers);
            transactions.addAll(restTemplate.exchange(baseApiUrl + "/accounts/" + accountId + "/transactions", HttpMethod.GET, entity2, MacTransactionResponse.class)
                .getBody().getTransactions().stream()
                .map((macTransaction -> Transaction.builder()
                    .accountId(accountId)
                    .amount(macTransaction.getAmount())
                    .category(macTransaction.getCategory())
                    //.createdAt(macTransaction.getTransaction_date())
                    .currencyCode(macTransaction.getCurrency_code())
                    .debit(macTransaction.getCr_dr_flag() == "DR")
                    .description(macTransaction.getDescription())
                    //.effectiveDate()
                    //.lastUpdated()
                    .merchant(macTransaction.getMerchant())
                    .transactionId(macTransaction.getId())
                    .build()))
                .collect(Collectors.toList()));

        });

        transactionRepository.save(transactions);
        return accountIds;
    }
}
