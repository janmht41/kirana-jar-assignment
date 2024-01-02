package com.assignment.KiranaService.service;

import com.assignment.KiranaService.model.TransactionRequest;
import com.assignment.KiranaService.model.TransactionSummaryDto;

import java.time.LocalDate;
import java.util.List;

public interface HandleTransactionRecord {
    public List<TransactionSummaryDto> getTransactions(LocalDate tranDate);
    public void saveKiranaTransaction(TransactionRequest transactionRequest);
}
