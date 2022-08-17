package com.example.fcmbassessment.controller;

import com.example.fcmbassessment.request.AirtimeRequest;
import com.example.fcmbassessment.request.TransferRequest;
import com.example.fcmbassessment.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("airtime/{customerId}")
    public ResponseEntity<String> airtimePurchase(@PathVariable Long customerId, @RequestBody AirtimeRequest airtimeRequest){
        return transactionService.airtimePurchase(customerId, airtimeRequest);
    }

    @PostMapping("transfer/{customerId}")
    public ResponseEntity<String> transfer(@PathVariable Long customerId, @RequestBody TransferRequest transferRequest){
        return transactionService.transfers(customerId, transferRequest);
    }

}
