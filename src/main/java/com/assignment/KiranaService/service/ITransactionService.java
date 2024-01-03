package com.assignment.KiranaService.service;

import com.assignment.KiranaService.entity.Transaction;
import com.assignment.KiranaService.model.TransactionRequestModel;
import com.assignment.KiranaService.model.TransactionSummaryDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
public interface ITransactionService {
    public TransactionSummaryDto getTransactionSummaryOn(LocalDate tranDate);
    public UUID saveTransaction(TransactionRequestModel transactionRequestModel);
    public List<Transaction> getTransactionsByDate(LocalDate localDate);
}
