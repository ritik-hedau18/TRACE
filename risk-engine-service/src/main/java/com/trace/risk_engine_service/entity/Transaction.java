package com.trace.risk_engine_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String senderEmail;
    private String receiverAccount;
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private com.trace.risk_engine_service.enums.TransactionType type;

    @Enumerated(EnumType.STRING)
    private com.trace.risk_engine_service.enums.TransactionStatus status;

    private String deviceId;
    private LocalDateTime createdAt;

}
