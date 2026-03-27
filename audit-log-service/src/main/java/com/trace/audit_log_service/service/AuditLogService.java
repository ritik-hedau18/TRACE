package com.trace.audit_log_service.service;

import com.trace.audit_log_service.document.AuditLog;
import com.trace.audit_log_service.dto.RiskResult;
import com.trace.audit_log_service.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public void saveAuditLog(RiskResult result) {
        AuditLog auditLog = AuditLog.builder()
                .transactionId(result.getTransactionId())
                .senderEmail(result.getSenderEmail())
                .riskScore(result.getRiskScore())
                .riskLevel(result.getRiskLevel())
                .finalStatus(result.getFinalStatus())
                .triggeredRules(result.getReason())
                .build();

        auditLogRepository.save(auditLog);

        log.info("Audit log saved | transactionId: {} | status: {} | score: {}",
                result.getTransactionId(),
                result.getFinalStatus(),
                result.getRiskScore());
    }

}
