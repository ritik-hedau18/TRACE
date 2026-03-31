package com.trace.risk_engine_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import com.trace.risk_engine_service.service.RiskEngineService;
import com.trace.risk_engine_service.entity.Transaction;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionConsumer {

    private final RiskEngineService riskEngineService;

    @KafkaListener(topics = "transaction-events", groupId = "risk-engine-group")
    public void consume(Transaction transaction) {
        log.info("Transaction received from Kafka | id: {} | amount: {}",
                transaction.getId(), transaction.getAmount());
        riskEngineService.processTransaction(transaction);
    }

}
