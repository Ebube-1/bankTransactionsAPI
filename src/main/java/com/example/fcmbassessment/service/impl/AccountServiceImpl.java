package com.example.fcmbassessment.service.impl;

import com.example.fcmbassessment.model.Account;
import com.example.fcmbassessment.model.Customer;
import com.example.fcmbassessment.repository.AccountRepository;
import com.example.fcmbassessment.repository.CustomerRepository;
import com.example.fcmbassessment.request.AccountRequest;
import com.example.fcmbassessment.service.AccountService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public ResponseEntity<String> createAccount(Long customerId, AccountRequest accountRequest) {

        Customer customer = customerRepository.findById(customerId).orElse(null);
        if(customer == null){
            return new ResponseEntity<>("customer does not exist", HttpStatus.NOT_FOUND);
        }
        if(!customer.getAccountNumber().equals(accountRequest.getAccountNumber())){
            return new ResponseEntity<>("Account Number not correct", HttpStatus.NOT_FOUND);
        }

        boolean byAccountNumber = accountRepository.existsByAccountNumber(accountRequest.getAccountNumber());
        if(byAccountNumber){
            return new ResponseEntity<>("Account with account Number already exists", HttpStatus.BAD_REQUEST);
        }

            Account account = Account.builder()
                    .accountBalance(getAccountBalance(accountRequest.getAccountNumber()))
                    .accountOpened(LocalDateTime.now())
                    .accountNumber(accountRequest.getAccountNumber())
                    .customer(customer)
                    .build();

            accountRepository.save(account);
            customerRepository.save(customer);

        return new ResponseEntity<>("account created", HttpStatus.CREATED);
    }

   // generate random account balance
    private BigDecimal getAccountBalance(String accountNumber){
        return BigDecimal.valueOf(new Random().nextDouble() * 1_000_001)
                .setScale(2, RoundingMode.HALF_UP);

    }

}
