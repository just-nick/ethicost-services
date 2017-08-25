package com.ethicost.transaction;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

public class TransactionController {

    @RestController
    @RequestMapping("/api")
    public class ScoreController {
        @GetMapping("/transactions")
        @ResponseBody
        public List<TransactionResponse> getTransactions() {
            return new ArrayList<>();
        }
    }

}
