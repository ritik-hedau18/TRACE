package com.trace.alert_service.dto;

import com.trace.alert_service.enums.RiskLevel;
import com.trace.alert_service.enums.TransactionStatus;
import lombok.*;

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
