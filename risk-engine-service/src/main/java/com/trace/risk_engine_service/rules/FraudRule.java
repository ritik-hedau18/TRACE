package com.trace.risk_engine_service.rules;
import com.trace.risk_engine_service.entity.Transaction;

public interface FraudRule {

    int evaluate(Transaction transaction);
    String getRuleName();

}
