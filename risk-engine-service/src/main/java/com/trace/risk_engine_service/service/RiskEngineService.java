package com.trace.risk_engine_service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.trace.risk_engine_service.rules.RuleEngine;
import com.trace.risk_engine_service.repository.TransactionRepository;
import com.trace.risk_engine_service.kafka.RiskResultProducer;
import com.trace.risk_engine_service.entity.Transaction;
import com.trace.risk_engine_service.dto.RiskResult;

@Service
@RequiredArgsConstructor
@Slf4j
public class RiskEngineService {

    private final RuleEngine ruleEngine;
    private final TransactionRepository transactionRepository;
    private final RiskResultProducer riskResultProducer;

    public void processTransaction(Transaction transaction) {

        RiskResult result = ruleEngine.evaluate(transaction);

        // DB me status update
        transactionRepository.findById(transaction.getId()).ifPresent(t -> {
            t.setStatus(result.getFinalStatus());
            transactionRepository.save(t);
            log.info("Transaction {} status updated to {}",
                    t.getId(), result.getFinalStatus());
        });

        // Result Kafka pe publish for Alert Service
        riskResultProducer.publishResult(result);
    }

}
