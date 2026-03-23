package com.trace.alert_service.service;

import com.trace.alert_service.dto.RiskResult;
import com.trace.alert_service.enums.TransactionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailAlertService {

    private final JavaMailSender mailSender;

    @Value("${alert.email.to}")
    private String alertEmailTo;

    public void sendFraudAlert(RiskResult result) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(alertEmailTo);
            message.setSubject("🚨 TRACE ALERT: Suspicious Transaction Detected");
            message.setText(buildEmailBody(result));

            mailSender.send(message);
            log.info("Alert email sent for transaction: {}", result.getTransactionId());

        } catch (Exception e) {
            log.error("Failed to send alert email for transaction: {} | Error: {}",
                    result.getTransactionId(), e.getMessage());
        }
    }

    private String buildEmailBody(RiskResult result) {
        return """
                ⚠️ TRACE FRAUD DETECTION ALERT
                ================================
                
                Transaction ID  : %d
                Sender Email    : %s
                Risk Score      : %d
                Risk Level      : %s
                Final Status    : %s
                Triggered Rules : %s
                
                Please review this transaction immediately.
                
                — TRACE System
                """.formatted(
                result.getTransactionId(),
                result.getSenderEmail(),
                result.getRiskScore(),
                result.getRiskLevel(),
                result.getFinalStatus(),
                result.getReason()
        );
    }

}
