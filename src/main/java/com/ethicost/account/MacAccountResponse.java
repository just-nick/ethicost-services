package com.ethicost.account;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MacAccountResponse {
    private List<Account> accounts;
}
