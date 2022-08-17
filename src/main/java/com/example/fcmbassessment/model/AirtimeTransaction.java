package com.example.fcmbassessment.model;

import com.example.fcmbassessment.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AirtimeTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    private String sourceAccount;

    private String networkProvider;

    private String phoneNumber;

    private BigDecimal amount;

    private LocalDateTime transactionDate;

    @JsonIgnore
    @JoinColumn(name = "customer_id")
    @ManyToOne
    private Customer customer;
}
