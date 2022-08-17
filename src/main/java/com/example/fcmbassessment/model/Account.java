package com.example.fcmbassessment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "accounts", uniqueConstraints = {@UniqueConstraint(columnNames = {"accountNumber"})})
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    private String accountNumber;

    @JoinColumn
    @JsonIgnore
    @ManyToOne
    private Customer customer;

    @PositiveOrZero
    private BigDecimal accountBalance;

    private LocalDateTime accountOpened;

}
