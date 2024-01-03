package com.assignment.KiranaService.service;

import com.assignment.KiranaService.entity.Transaction;
import com.assignment.KiranaService.model.ExchangeResponse;
import com.assignment.KiranaService.model.TransactionRequest;

import com.assignment.KiranaService.model.TransactionSummaryDto;
import com.assignment.KiranaService.repository.KiranaTransactionRepository;
import com.assignment.KiranaService.utility.ExchangeResponseUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HandleTransactionRecordImpl implements HandleTransactionRecord{
    public static final String CREDIT = "CREDIT";
    public static final String DEBIT = "DEBIT";

    @Autowired
    private  KiranaTransactionRepository kiranaTransactionRepository;

    @Autowired
    private ExchangeResponseUtil exchangeResponseUtil;

    @Override
    public List<TransactionSummaryDto> getTransactions(LocalDate tranDate){
        if(tranDate == null) tranDate = LocalDate.now();
        return kiranaTransactionRepository.getTransactionSummaryByDate(tranDate);
    }

    public void saveKiranaTransaction(TransactionRequest transactionRequest){
         Double conversionFactor = Double.valueOf(1.0);
        if(transactionRequest.getCurrency().equalsIgnoreCase("USD")) {
             ExchangeResponse exchangeResponse = exchangeResponseUtil.getExchangeRate();
             conversionFactor = exchangeResponse.getRates().get("INR");
        }
        kiranaTransactionRepository.save(mapToKiranaTransaction(transactionRequest, conversionFactor));

    }

    private Transaction mapToKiranaTransaction(TransactionRequest transactionRequest, Double conversionFactor){
       String transactionType = transactionRequest.getTransactionType();
       Double value = transactionRequest.getAmount();
       Double debitAmount =isDebitType(transactionType) ? value:Double.valueOf(0) ;
       Double creditAmount = isCreditType(transactionType)? value: Double.valueOf(0);
        debitAmount *= conversionFactor;
        creditAmount *= conversionFactor;

       return Transaction.builder()
                .transactionId(UUID.randomUUID())
                .creditAmount(creditAmount)
                .debitAmount(debitAmount)
                .transactionTime(LocalDateTime.now())
                .transactionDetails(transactionRequest.getTransactionDesc())
                .build();

    }

    private boolean isCreditType(String transactionType) {
        return transactionType.equalsIgnoreCase(CREDIT);
    }

    private boolean isDebitType(String transactionType){
        return transactionType.equalsIgnoreCase(DEBIT);
    }
}
