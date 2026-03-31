package com.trace.risk_engine_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import com.trace.risk_engine_service.dto.RiskResult;

@Component
@RequiredArgsConstructor
@Slf4j
public class RiskResultProducer {

    private static final String TOPIC = "risk-results";

    private final KafkaTemplate<String, RiskResult> kafkaTemplate;

    public void publishResult(RiskResult result) {
        kafkaTemplate.send(TOPIC, result.getSenderEmail(), result);
        log.info("Risk result published | transactionId: {} | status: {}",
                result.getTransactionId(), result.getFinalStatus());
    }

}
