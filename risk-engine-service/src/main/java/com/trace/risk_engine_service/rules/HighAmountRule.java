package com.trace.risk_engine_service.rules;

import org.springframework.stereotype.Component;
import com.trace.risk_engine_service.rules.FraudRule;
import com.trace.risk_engine_service.entity.Transaction;
import java.math.BigDecimal;

@Component
public class HighAmountRule implements FraudRule {

    private static final BigDecimal THRESHOLD = new BigDecimal("100000");

    @Override
    public int evaluate(Transaction transaction) {
        return transaction.getAmount().compareTo(THRESHOLD) > 0 ? 40 : 0;
    }

    @Override
    public String getRuleName() {
        return "HIGH_AMOUNT_RULE";
    }

}
