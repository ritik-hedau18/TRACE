package com.trace.risk_engine_service.rules;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.trace.risk_engine_service.entity.Transaction;
import com.trace.risk_engine_service.rules.FraudRule;
import com.trace.risk_engine_service.dto.RiskResult;
import com.trace.risk_engine_service.enums.RiskLevel;
import com.trace.risk_engine_service.enums.TransactionStatus;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RuleEngine {

    private final List<FraudRule> rules;

    public RiskResult evaluate(Transaction transaction) {
        int totalScore = 0;
        StringBuilder reasons = new StringBuilder();

        for (FraudRule rule : rules) {
            int score = rule.evaluate(transaction);
            if (score > 0) {
                totalScore += score;
                reasons.append(rule.getRuleName()).append(" ");
            }
        }

        RiskLevel riskLevel = calculateRiskLevel(totalScore);
        TransactionStatus status = calculateStatus(riskLevel);

        log.info("Transaction {} | Score: {} | Risk: {} | Status: {}",
                transaction.getId(), totalScore, riskLevel, status);

        return RiskResult.builder()
                .transactionId(transaction.getId())
                .senderEmail(transaction.getSenderEmail())
                .riskScore(totalScore)
                .riskLevel(riskLevel)
                .finalStatus(status)
                .reason(reasons.toString().trim())
                .build();
    }

    private RiskLevel calculateRiskLevel(int score) {
        if (score <= 20) return RiskLevel.LOW;
        if (score <= 40) return RiskLevel.MEDIUM;
        if (score <= 70) return RiskLevel.HIGH;
        return RiskLevel.CRITICAL;
    }

    private TransactionStatus calculateStatus(RiskLevel riskLevel) {
        return switch (riskLevel) {
            case LOW -> TransactionStatus.APPROVED;
            case MEDIUM, HIGH -> TransactionStatus.FLAGGED;
            case CRITICAL -> TransactionStatus.BLOCKED;
        };
    }

}
