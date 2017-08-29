package com.ethicost.transaction;

import com.ethicost.account.Account;
import com.ethicost.account.AccountService;
import com.ethicost.account.MacAccountResponse;
import com.ethicost.merchant.MerchantService;
import com.ethicost.merchant.MerchantTransactionResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserTransactionController {

        private TransactionService transactionService;

        private AccountService accountService;

        @GetMapping("/user-transactions")
        @ResponseBody
        public List<MerchantTransactionResponse> getTransactions(@RequestHeader(value="Authorization") String authorizationHeader) {
            List<String> accountIds = accountService.getAccounts(authorizationHeader);

            // get all accounts
            // create a list of account ID's
            Map<String, List<TransactionResponse>> stringListMap = new HashMap<>();
            transactionService.findAllTransactions(accountIds).stream()
                .map((transaction) -> {
                    TransactionResponse transactionResponse = new TransactionResponse();
                    BeanUtils.copyProperties(transaction, transactionResponse);
                    return transactionResponse;
                })
                .forEach((transactionResponse) -> {
                    if(!stringListMap.keySet().contains(transactionResponse.getMerchant())) {
                        stringListMap.put(transactionResponse.getMerchant(), new ArrayList<>());
                    }

                    stringListMap.get(transactionResponse.getMerchant()).add(transactionResponse);
                });


            Random rand = new Random();

            List<MerchantTransactionResponse> response = stringListMap.keySet().stream()
                .map((key)->{
                    List<TransactionResponse> transactions = stringListMap.get(key);
                    return MerchantTransactionResponse.builder()
                        .category(transactions.get(0).getCategory())
                        .transactionResponses(transactions)
                        .merchantName(key)
                        .rating(rand.nextInt(100))
                        .build();
                })
                .collect(Collectors.toList());
            return response;
        }

}
