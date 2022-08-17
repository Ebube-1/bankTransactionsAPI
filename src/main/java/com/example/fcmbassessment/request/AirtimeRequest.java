package com.example.fcmbassessment.request;

import com.example.fcmbassessment.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AirtimeRequest {

    private String sourceAccount;

    private String networkProvider;

    private BigDecimal amount;

    private String phoneNumber;
}
