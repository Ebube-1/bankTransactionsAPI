package com.example.fcmbassessment.service.impl;

import com.example.fcmbassessment.enums.CustomerType;
import com.example.fcmbassessment.enums.TransactionType;
import com.example.fcmbassessment.model.Account;
import com.example.fcmbassessment.model.AirtimeTransaction;
import com.example.fcmbassessment.model.Customer;
import com.example.fcmbassessment.model.TransferTransaction;
import com.example.fcmbassessment.repository.AccountRepository;
import com.example.fcmbassessment.repository.AirtimeRepository;
import com.example.fcmbassessment.repository.CustomerRepository;
import com.example.fcmbassessment.repository.TransactionRepository;
import com.example.fcmbassessment.request.AirtimeRequest;
import com.example.fcmbassessment.request.TransferRequest;
import com.example.fcmbassessment.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;


@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final AirtimeRepository airtimeRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public ResponseEntity<String> airtimePurchase (Long customerId, AirtimeRequest airtimeRequest){
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("customer does not exist"));

        if(!customer.getAccountNumber().equals(airtimeRequest.getSourceAccount())){
            return new ResponseEntity<>("Source account does not belong to you", HttpStatus.NOT_FOUND);
        }

        Account account = accountRepository.findByAccountNumber(airtimeRequest.getSourceAccount());
        if(account == null){
            return new ResponseEntity<>("account not found", HttpStatus.NOT_FOUND);
        }

        BigDecimal balance = account.getAccountBalance();
        BigDecimal atAmount = airtimeRequest.getAmount();

        if(atAmount.compareTo(balance) > 0 ) {
            return new ResponseEntity<>("insufficient funds", HttpStatus.BAD_REQUEST);
        }

        AirtimeTransaction airtimeTransaction = AirtimeTransaction.builder()
                .customer(customer)
                .transactionDate(LocalDateTime.now())
                .transactionType(TransactionType.AIRTIME)
                .amount(atAmount)
                .networkProvider(airtimeRequest.getNetworkProvider())
                .phoneNumber(airtimeRequest.getPhoneNumber())
                .sourceAccount(airtimeRequest.getSourceAccount())
                .build();
        account.setAccountBalance(balance.subtract(atAmount));

        airtimeRepository.save(airtimeTransaction);

        accountRepository.save(account);

        return new ResponseEntity<>("Airtime purchase successful", HttpStatus.OK);
    }

    @Transactional
    public ResponseEntity<String> transfers (Long customerId, TransferRequest transferRequest){

        Customer customer = customerRepository.findById(customerId).orElseThrow(null);
        if (customer == null)
            return new ResponseEntity<>("Customer not found", HttpStatus.NOT_FOUND);

        Account account = accountRepository.findByAccountNumber(transferRequest.getSourceAccount());
        if(account == null){
            return new ResponseEntity<>("account not found", HttpStatus.NOT_FOUND);
        }

        if(!customer.getAccounts().contains(account)){
            return new ResponseEntity<>("account number does not belong to you", HttpStatus.NOT_FOUND);
        }

        Account account1 = accountRepository.findByAccountNumber(transferRequest.getDestinationAccount());
        if(account1 == null){
            return new ResponseEntity<>("kindly check destination account", HttpStatus.NOT_FOUND);
        }

        BigDecimal balance = account.getAccountBalance();
        BigDecimal trAmount = transferRequest.getAmount();
        BigDecimal discount = BigDecimal.valueOf(0.00);

        if(trAmount.compareTo(balance) > 0){
            return new ResponseEntity<>("Insufficient funds", HttpStatus.BAD_REQUEST);
        }

        int transactionsWithinAMonth = transactionRepository.countTransactionsByCustomerWithinAMonth(customerId);

        if(Period.between(customer.getDateCreated().toLocalDate(), LocalDate.now()).getYears() > 4 &&
            transactionsWithinAMonth < 4){
            discount = trAmount.subtract((BigDecimal.valueOf(0.10).multiply(trAmount)));
        }

        if(customer.getCustomerType() == CustomerType.BUSINESS &&
                transactionsWithinAMonth > 3 &&
                trAmount.compareTo(BigDecimal.valueOf(150000)) > 0){
            discount = trAmount.subtract((BigDecimal.valueOf(0.27).multiply(trAmount)));

        } else if (customer.getCustomerType() == CustomerType.RETAIL &&
                transactionsWithinAMonth > 3 &&
                trAmount.compareTo(BigDecimal.valueOf(50000)) > 0){
            discount = trAmount.subtract((BigDecimal.valueOf(0.18).multiply(trAmount)));
        }

        TransferTransaction transferTransaction = TransferTransaction.builder()
                .transactionType(TransactionType.TRANSFER)
                .transactionDate(LocalDateTime.now())
                .destinationAccount(transferRequest.getDestinationAccount())
                .customer(customer)
                .destinationAccount(transferRequest.getDestinationAccount())
                .amount(trAmount)
                .build();

        account.setAccountBalance(account.getAccountBalance().subtract(transferRequest.getAmount()).add(discount));
        account1.setAccountBalance(transferRequest.getAmount().add(account1.getAccountBalance()));

        accountRepository.save(account);

        transactionRepository.save(transferTransaction);

        return new ResponseEntity<>("transaction to "+ transferRequest.getDestinationAccount() + " successful", HttpStatus.OK);
    }




}
