package com.assignment.KiranaService.service;

import com.assignment.KiranaService.model.TransactionRequest;
import com.assignment.KiranaService.model.TransactionResponse;

import java.time.LocalDate;
import java.util.List;

public interface HandleTransactionRecord {
    public List<TransactionResponse> getTransactions(LocalDate tranDate);
    public void saveKiranaTransaction(TransactionRequest transactionRequest);
}
