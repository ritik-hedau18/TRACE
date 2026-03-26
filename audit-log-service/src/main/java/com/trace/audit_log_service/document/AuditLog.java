package com.trace.audit_log_service.document;

import com.trace.audit_log_service.enums.RiskLevel;
import com.trace.audit_log_service.enums.TransactionStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "audit_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuditLog {

    @Id
    private String id;

    private Long transactionId;
    private String senderEmail;
    private int riskScore;
    private RiskLevel riskLevel;
    private TransactionStatus finalStatus;
    private String triggeredRules;

    @Builder.Default
    private LocalDateTime auditedAt = LocalDateTime.now();

}
