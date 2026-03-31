package com.trace.risk_engine_service.dto;

import lombok.*;
import com.trace.risk_engine_service.enums.RiskLevel;
import com.trace.risk_engine_service.enums.TransactionStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiskResult {

    private Long transactionId;
    private String senderEmail;
    private int riskScore;
    private RiskLevel riskLevel;
    private TransactionStatus finalStatus;
    private String reason;

}
