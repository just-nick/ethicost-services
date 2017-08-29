package com.ethicost.account;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MacTransactionResponse {
    List<MacTransaction> transactions;
}
