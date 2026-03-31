package com.trace.risk_engine_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.trace.risk_engine_service.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,  Long> {

    long countBySenderEmailAndCreatedAtAfter(String senderEmail, LocalDateTime after);

    List<Transaction> findBySenderEmail(String senderEmail);

}
