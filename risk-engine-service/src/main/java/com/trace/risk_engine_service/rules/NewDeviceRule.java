package com.trace.risk_engine_service.rules;

import com.trace.risk_engine_service.rules.FraudRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import com.trace.risk_engine_service.repository.TransactionRepository;
import com.trace.risk_engine_service.entity.Transaction;

@Component
@RequiredArgsConstructor
public class NewDeviceRule implements FraudRule {


    private final TransactionRepository transactionRepository;

    @Override
    public int evaluate(Transaction transaction) {
        boolean seenBefore = transactionRepository
                .findBySenderEmail(transaction.getSenderEmail())
                .stream()
                .anyMatch(t -> t.getDeviceId() != null
                        && t.getDeviceId().equals(transaction.getDeviceId())
                        && !t.getId().equals(transaction.getId()));

        return seenBefore ? 0 : 25;
    }

    @Override
    public String getRuleName() {
        return "NEW_DEVICE_RULE";
    }

}
