package com.ethicost.transaction;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class TransactionResponse {

    private String userId;

    private String accountId;

    private String transactionId;

    private BigDecimal amount;

    private String description;

    private String type;

    private Date transactionDate;

    private Date effectiveDate;

    private Boolean debit;

    private String category;

    private BigDecimal runningBalance;

    private String currencyCode;

    private String merchant;

    private String latitude;

    private String longitude;

    private Date createdAt;

    private Date lastUpdated;
}
