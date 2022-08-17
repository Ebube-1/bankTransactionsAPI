package com.example.fcmbassessment.repository;

import com.example.fcmbassessment.enums.CustomerType;
import com.example.fcmbassessment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findCustomerByEmail (String email);
    boolean existsByEmail (String email);

}
