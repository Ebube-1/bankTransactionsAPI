package com.example.fcmbassessment.repository;

import com.example.fcmbassessment.model.TransferTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransferTransaction, Long> {

    @Query(value =
            "SELECT COUNT(*) FROM transfer_transactions " +
            "WHERE customer_id = ?1 AND transaction_type = 'TRANSFER' " +
            "AND extract(year from transaction_date) = extract(year from CURRENT_DATE) AND extract(month from transaction_date) = extract(month from CURRENT_DATE)",
    nativeQuery = true)
    int countTransactionsByCustomerWithinAMonth(Long customerId);

}

