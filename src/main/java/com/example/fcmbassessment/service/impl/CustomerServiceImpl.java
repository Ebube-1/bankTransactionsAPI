package com.example.fcmbassessment.service.impl;

import com.example.fcmbassessment.model.Customer;
import com.example.fcmbassessment.repository.CustomerRepository;
import com.example.fcmbassessment.request.CustomerRequest;
import com.example.fcmbassessment.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public ResponseEntity<String> createCustomer(CustomerRequest customerRequest){
        boolean existsByEmail = customerRepository.existsByEmail(customerRequest.getEmail());
        if(existsByEmail){
            return new ResponseEntity<>("customer with " + customerRequest.getEmail() + " already exists", HttpStatus.BAD_REQUEST);
        }
        Customer customer = Customer.builder()
                .customerName(customerRequest.getCustomerName())
                .customerType(customerRequest.getCustomerType())
                .age(customerRequest.getAge())
                .email(customerRequest.getEmail())
                .phoneNumber(customerRequest.getPhoneNumber())
                .dateCreated(LocalDateTime.now())
                .customerType(customerRequest.getCustomerType())
                .accountNumber(getAccountNumber())
                .build();
        customerRepository.save(customer);

        return new ResponseEntity<>(customerRequest.getCustomerName() + ", you are now part of our esteemed customers", HttpStatus.CREATED);
    }

    private String getAccountNumber(){

        return String.valueOf(new Random().nextLong(1111111111, 2111111111));
    }

}
