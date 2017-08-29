package com.ethicost.account;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class MacTransaction {
      private String id;
      private BigDecimal amount;
      private String description;
      private String type;
      private String transaction_date;
      private String effective_date;
      private String cr_dr_flag;
      private String category;
      private BigDecimal running_balance;
      private String currency_code;
      private String merchant;
}
