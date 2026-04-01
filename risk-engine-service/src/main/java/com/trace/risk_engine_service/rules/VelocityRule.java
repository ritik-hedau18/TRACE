package com.trace.risk_engine_service.rules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.trace.risk_engine_service.rules.FraudRule;
import com.trace.risk_engine_service.repository.TransactionRepository;
import com.trace.risk_engine_service.entity.Transaction;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class VelocityRule implements FraudRule {

    private final TransactionRepository transactionRepository;

    @Override
    public int evaluate(Transaction transaction) {
        LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);
        long count = transactionRepository.countBySenderEmailAndCreatedAtAfter(
                transaction.getSenderEmail(), fiveMinutesAgo);
        return count >= 3 ? 30 : 0;
    }

    @Override
    public String getRuleName() {
        return "VELOCITY_RULE";
    }

}
