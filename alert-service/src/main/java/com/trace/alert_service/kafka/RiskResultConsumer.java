package com.trace.alert_service.kafka;

import com.trace.alert_service.dto.RiskResult;
import com.trace.alert_service.enums.TransactionStatus;
import com.trace.alert_service.service.EmailAlertService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RiskResultConsumer {

    private final EmailAlertService emailAlertService;

    @KafkaListener(topics = "risk-results", groupId = "alert-service-group")
    public void consume(RiskResult result) {
        log.info("Risk result received | transactionId: {} | status: {}",
                result.getTransactionId(), result.getFinalStatus());

        if (result.getFinalStatus() == TransactionStatus.FLAGGED ||
                result.getFinalStatus() == TransactionStatus.BLOCKED) {

            log.warn("Suspicious transaction detected! Sending alert...");
            emailAlertService.sendFraudAlert(result);
        } else {
            log.info("Transaction {} is APPROVED — no alert needed.", result.getTransactionId());
        }
    }

}
