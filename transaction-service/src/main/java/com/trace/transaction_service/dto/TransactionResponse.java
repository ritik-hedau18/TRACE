package com.trace.transaction_service.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import com.trace.transaction_service.enums.TransactionStatus;
import com.trace.transaction_service.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class TransactionResponse {

    private Long id;
    private String senderEmail;
    private String receiverAccount;
    private BigDecimal amount;
    private TransactionType type;
    private TransactionStatus status;
    private LocalDateTime createdAt;

}
