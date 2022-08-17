package com.example.fcmbassessment.controller;

import com.example.fcmbassessment.request.AccountRequest;
import com.example.fcmbassessment.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts/")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("{customerId}")
    public ResponseEntity<String> createAccount (@PathVariable Long customerId, @Valid @RequestBody AccountRequest accountRequest){
        return accountService.createAccount(customerId, accountRequest);
    }
}
