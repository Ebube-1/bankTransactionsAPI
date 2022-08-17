package com.example.fcmbassessment.service;

import com.example.fcmbassessment.request.CustomerRequest;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<String> createCustomer(CustomerRequest customerRequest);
}
