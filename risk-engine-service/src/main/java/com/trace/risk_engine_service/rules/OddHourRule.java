package com.trace.risk_engine_service.rules;

import org.springframework.stereotype.Component;
import com.trace.risk_engine_service.rules.FraudRule;
import com.trace.risk_engine_service.entity.Transaction;

import java.time.LocalTime;

@Component
public class OddHourRule implements FraudRule {


    @Override
    public int evaluate(Transaction transaction) {
        int hour = LocalTime.now().getHour();
        return (hour >= 1 && hour <= 4) ? 20 : 0;
    }

    @Override
    public String getRuleName() {
        return "ODD_HOUR_RULE";
    }

}
