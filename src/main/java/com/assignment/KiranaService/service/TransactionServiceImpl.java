package com.assignment.KiranaService.service;

import com.assignment.KiranaService.entity.Transaction;
import com.assignment.KiranaService.model.ExchangeResponseModel;
import com.assignment.KiranaService.model.TransactionRequestModel;
import com.assignment.KiranaService.model.TransactionSummaryDto;
import com.assignment.KiranaService.repository.TransactionRepository;
import com.assignment.KiranaService.utility.ExchangeResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static com.assignment.KiranaService.utility.Constants.*;
import static java.util.Objects.*;

/**
 * Service implementation for managing Kirana store transactions.
 */
@Service
@AllArgsConstructor
public class TransactionServiceImpl implements ITransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ExchangeResponseUtil exchangeResponseUtil;

    /**
     * Retrieves a summary of transactions for a given date.
     *
     * @param tranDate The date for which the transaction summary is requested.
     * @return TransactionSummaryDto containing details of the transaction summary.
     */
    @Override
    public TransactionSummaryDto getTransactionSummaryOn(LocalDate tranDate) {
        if (isNull(tranDate)) tranDate = LocalDate.now();
        var transactionSummary = transactionRepository.getTransactionSummary(tranDate);
        return Objects.isNull(transactionSummary) ?
                TransactionSummaryDto.builder()
                        .transactionDate(Date.valueOf(tranDate))
                        .creditAmount(0.0).debitAmount(0.0)
                        .build() : transactionSummary;
    }

    /**
     * Saves a new transaction based on the provided transaction request.
     *
     * @param transactionRequestModel The request containing transaction details.
     */
    @Override
    public UUID saveTransaction(TransactionRequestModel transactionRequestModel) {
        var conversionFactor = Double.valueOf(1.0);
        if (transactionRequestModel.getCurrency().equalsIgnoreCase(USD)) {
            ExchangeResponseModel exchangeResponseModel = exchangeResponseUtil.getExchangeRateInfo();
            conversionFactor = exchangeResponseModel.getRates().get(INR);
        }
        var transaction = transformToTransaction(transactionRequestModel, conversionFactor);
        transactionRepository.save(transaction);
        return transaction.getTransactionId();
    }

    /**
     * Retrieves a list of transactions for a given date.
     *
     * @param localDate The date for which transactions are requested.
     * @return List of transactions for the specified date.
     */
    @Override
    public List<Transaction> getTransactionsByDate(LocalDate localDate) {
        var startDateTime = localDate.atStartOfDay();
        var endDateTime = localDate.plusDays(1).atStartOfDay().minusSeconds(1);
        return transactionRepository.findAllByTransactionTimeBetween(startDateTime, endDateTime);
    }

    /**
     * Transforms request object to Transaction Entity object
     * @param transactionRequestModel Object received from record Transaction api
     * @param conversionFactor currency conversion factor for non INR transactions
     * @return Transaction entity class object to be persisted in db
     */
    private Transaction transformToTransaction(TransactionRequestModel transactionRequestModel, Double conversionFactor) {
        String transactionType = transactionRequestModel.getTransactionType();
        Double value = transactionRequestModel.getAmount();
        Double debitAmount = isDebitType(transactionType) ? value : Double.valueOf(0);
        Double creditAmount = isCreditType(transactionType) ? value : Double.valueOf(0);

        return Transaction.builder()
                .transactionId(UUID.randomUUID())
                .creditAmount(creditAmount * conversionFactor)
                .debitAmount(debitAmount * conversionFactor)
                .transactionTime(LocalDateTime.now())
                .transactionDetails(transactionRequestModel.getTransactionDesc())
                .paymentMode(transactionRequestModel.getPaymentMode())
                .creationUser(transactionRequestModel.getCreationUser())
                .build();
    }

    private boolean isCreditType(String transactionType) {
        return transactionType.equalsIgnoreCase(CREDIT);
    }

    private boolean isDebitType(String transactionType) {
        return transactionType.equalsIgnoreCase(DEBIT);
    }
}
