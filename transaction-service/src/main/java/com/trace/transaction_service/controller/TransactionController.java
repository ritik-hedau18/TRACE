package com.trace.transaction_service.controller;

import com.trace.transaction_service.dto.TransactionResponse;
import com.trace.transaction_service.dto.TransactionRequest;
import com.trace.transaction_service.security.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
@Tag(name = "Transactions", description = "Submit and view transactions")
@SecurityRequirement(name = "bearerAuth")
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(summary = "Submit a new transaction for risk analysis")
    @PostMapping("/submit")
    public ResponseEntity<TransactionResponse> submit(
            @RequestBody TransactionRequest request,
            @AuthenticationPrincipal UserDetails userDetails) {

        String senderEmail = userDetails.getUsername();
        return ResponseEntity.ok(transactionService.submitTransaction(request, senderEmail));
    }

    @Operation(summary = "Get all transactions for the logged-in user")
    @GetMapping("/my")
    public ResponseEntity<List<TransactionResponse>> myTransactions(
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.ok(
                transactionService.getUserTransactions(userDetails.getUsername()));
    }

}
