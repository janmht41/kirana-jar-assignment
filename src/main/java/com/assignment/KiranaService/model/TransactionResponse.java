package com.assignment.KiranaService.model;

import java.time.LocalDateTime;



public class TransactionResponse {

    public Double getCreditAmount() {
        return creditAmount;
    }

    public Double getDebitAmount() {
        return debitAmount;
    }

    public String getTransactionDesc() {
        return transactionDesc;
    }

    public LocalDateTime getTransactionDateTime() {
        return transactionDateTime;
    }

    public TransactionResponse(Double creditAmount, Double debitAmount, String transactionDesc, LocalDateTime transactionDateTime) {
        this.creditAmount = creditAmount;
        this.debitAmount = debitAmount;
        this.transactionDesc = transactionDesc;
        this.transactionDateTime = transactionDateTime;
    }

    private Double creditAmount;
    private Double debitAmount;
    private String transactionDesc;

    private LocalDateTime transactionDateTime;
}
