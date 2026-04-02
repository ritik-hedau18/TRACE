package com.trace.transaction_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.trace.transaction_service.entity.Transaction;

@Component
@RequiredArgsConstructor
@Slf4j
public class TransactionProducer {

    private static final String TOPIC = "transaction-events";

    private final KafkaTemplate<String, Transaction> kafkaTemplate;

    public void publishTransaction(Transaction transaction) {
        kafkaTemplate.send(TOPIC, transaction.getSenderEmail(), transaction);
        log.info("Transaction published to Kafka | id: {} | amount: {}",
                transaction.getId(), transaction.getAmount());
    }

}
