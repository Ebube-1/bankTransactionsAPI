package com.example.fcmbassessment.service;

import com.example.fcmbassessment.request.AccountRequest;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    ResponseEntity<String> createAccount(Long customerId, AccountRequest accountRequest);
}
