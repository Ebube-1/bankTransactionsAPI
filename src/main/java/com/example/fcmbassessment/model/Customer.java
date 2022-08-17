package com.example.fcmbassessment.model;

import com.example.fcmbassessment.enums.CustomerType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @Column(nullable = false)
    private String customerName;

    private String email;

    private String phoneNumber;

    private int age;

    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customer")
    private List<Account> accounts;

    private LocalDateTime dateCreated;

    private String accountNumber;
}
