package com.trace.risk_engine_service.rules;

import org.springframework.stereotype.Component;
import com.trace.risk_engine_service.rules.FraudRule;
import com.trace.risk_engine_service.entity.Transaction;
import java.util.Set;

@Component
public class BlackListRule implements FraudRule {

    // Real system me yeh DB ya Redis se aata — abhi hardcode hai demo ke liye
    private static final Set<String> BLACKLISTED = Set.of(
            "BLOCKED_ACC_001",
            "BLOCKED_ACC_002",
            "FRAUD_ACC_999"
    );

    @Override
    public int evaluate(Transaction transaction) {
        return BLACKLISTED.contains(transaction.getReceiverAccount()) ? 50 : 0;
    }

    @Override
    public String getRuleName() {
        return "BLACKLIST_RULE";
    }

}
