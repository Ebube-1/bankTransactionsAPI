package com.example.fcmbassessment.repository;

import com.example.fcmbassessment.model.AirtimeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirtimeRepository extends JpaRepository<AirtimeTransaction, Long> {
}
