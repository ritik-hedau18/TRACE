package com.trace.transaction_service.security;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;
import com.trace.transaction_service.repository.TransactionRepository;
import com.trace.transaction_service.kafka.TransactionProducer;
import com.trace.transaction_service.dto.TransactionRequest;
import com.trace.transaction_service.dto.TransactionResponse;
import com.trace.transaction_service.entity.Transaction;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionProducer transactionProducer;

    public TransactionResponse submitTransaction(TransactionRequest request, String senderEmail) {

        Transaction transaction = Transaction.builder()
                .senderEmail(senderEmail)
                .receiverAccount(request.getReceiverAccount())
                .amount(request.getAmount())
                .type(request.getType())
                .deviceId(request.getDeviceId())
                .build();

        Transaction saved = transactionRepository.save(transaction);

        transactionProducer.publishTransaction(saved);

        log.info("Transaction saved and published | id: {}", saved.getId());

        return toResponse(saved);
    }

    public List<TransactionResponse> getUserTransactions(String senderEmail) {
        return transactionRepository.findBySenderEmail(senderEmail)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private TransactionResponse toResponse(Transaction t) {
        return TransactionResponse.builder()
                .id(t.getId())
                .senderEmail(t.getSenderEmail())
                .receiverAccount(t.getReceiverAccount())
                .amount(t.getAmount())
                .type(t.getType())
                .status(t.getStatus())
                .createdAt(t.getCreatedAt())
                .build();
    }
}
