package com.trace.transaction_service.entity;

import jakarta.persistence.*;
import lombok.*;
import com.trace.transaction_service.enums.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.trace.transaction_service.enums.TransactionStatus;

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

    @Column(nullable = false)
    private String senderEmail;

    @Column(nullable = false)
    private String receiverAccount;

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private TransactionStatus status = TransactionStatus.PENDING;

    private String deviceId;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

}
