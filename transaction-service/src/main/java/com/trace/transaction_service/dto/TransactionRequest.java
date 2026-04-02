package com.trace.transaction_service.dto;

import com.trace.transaction_service.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {

    private String receiverAccount;
    private BigDecimal amount;
    private TransactionType type;
    private String deviceId;

}
