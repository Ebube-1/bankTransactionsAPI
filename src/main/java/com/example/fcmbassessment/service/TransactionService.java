package com.example.fcmbassessment.service;

import com.example.fcmbassessment.request.AirtimeRequest;
import com.example.fcmbassessment.request.TransferRequest;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ResponseEntity<String> airtimePurchase (Long customerId, AirtimeRequest airtimeRequest);
    ResponseEntity<String> transfers (Long customerId, TransferRequest transferRequest);
}
