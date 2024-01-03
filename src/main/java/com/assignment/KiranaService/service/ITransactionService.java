package com.assignment.KiranaService.service;

import com.assignment.KiranaService.entity.Transaction;
import com.assignment.KiranaService.model.TransactionRequest;
import com.assignment.KiranaService.model.TransactionSummaryDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
    public TransactionSummaryDto getTransactionSummaryOn(LocalDate tranDate);
    public void saveTransaction(TransactionRequest transactionRequest);

    public List<Transaction> getTransactionsByDate(LocalDate localDate);
}
