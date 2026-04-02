package com.trace.transaction_service.repository;

import com.trace.transaction_service.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    List<Transaction> findBySenderEmail(String senderEmail);

    long countBySenderEmailAndCreatedAtAfter(String senderEmail, LocalDateTime after);

}
