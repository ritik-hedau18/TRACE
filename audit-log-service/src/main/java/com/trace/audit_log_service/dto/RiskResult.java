package com.trace.audit_log_service.dto;

import com.trace.audit_log_service.enums.RiskLevel;
import com.trace.audit_log_service.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RiskResult {

    private Long transactionId;
    private String senderEmail;
    private int riskScore;
    private RiskLevel riskLevel;
    private TransactionStatus finalStatus;
    private String reason;

}
