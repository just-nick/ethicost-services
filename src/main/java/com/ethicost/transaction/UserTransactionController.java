package com.ethicost.transaction;

import com.ethicost.account.AccountService;
import com.ethicost.merchant.MerchantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@AllArgsConstructor(onConstructor = @__({@Autowired}))
public class UserTransactionController {

        private MerchantService merchantService;

        private AccountService accountService;

        @GetMapping("/user-transactions")
        @ResponseBody
        public UserTransactionsResponse getTransactions(@RequestHeader(value="access-token") String accessToken) {
            ResponseEntity<List> accounts = accountService.getAccounts(accessToken);

            List accountsList = accounts.getBody();

            List<String> accountIds = new ArrayList<>();

            // get all accounts
            // create a list of account ID's
            merchantService.getMerchantTransactionsFor(accountIds);
            return null;
        }

}
