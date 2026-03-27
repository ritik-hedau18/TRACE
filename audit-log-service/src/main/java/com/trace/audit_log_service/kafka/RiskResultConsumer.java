package com.trace.audit_log_service.kafka;

import com.trace.audit_log_service.dto.RiskResult;
import com.trace.audit_log_service.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class RiskResultConsumer {

    private final AuditLogService auditLogService;

    @KafkaListener(topics = "risk-results", groupId = "audit-log-group")
    public void consume(RiskResult result) {
        log.info("Audit log consumer received | transactionId: {} | status: {}",
                result.getTransactionId(), result.getFinalStatus());
        auditLogService.saveAuditLog(result);
    }

}
