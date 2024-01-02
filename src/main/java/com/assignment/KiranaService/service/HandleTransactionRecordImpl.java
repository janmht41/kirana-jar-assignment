package com.assignment.KiranaService.service;

import com.assignment.KiranaService.entity.KiranaTransaction;
import com.assignment.KiranaService.model.ExchangeResponse;
import com.assignment.KiranaService.model.TransactionRequest;

import com.assignment.KiranaService.model.TransactionSummaryDto;
import com.assignment.KiranaService.repository.KiranaTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HandleTransactionRecordImpl implements HandleTransactionRecord{
    @Autowired
    private  KiranaTransactionRepository kiranaTransactionRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Override
    public List<TransactionSummaryDto> getTransactions(LocalDate tranDate){
        if(tranDate == null) tranDate = LocalDate.now();
        return kiranaTransactionRepository.getTransactionSummaryByDate(tranDate);
    }

    public void saveKiranaTransaction(TransactionRequest transactionRequest){
         Double conversionFactor = Double.valueOf(1.0);
        if(transactionRequest.getCurrency().equalsIgnoreCase("USD")) {
            ExchangeResponse exchangeResponse = webClientBuilder.build()
                    .get()
                    .uri("https://api.fxratesapi.com/latest?base=USD&currencies=INR")
                    .retrieve()
                    .bodyToMono(ExchangeResponse.class)
                    .block();
             conversionFactor = exchangeResponse.getRates().get("INR");
        }
        kiranaTransactionRepository.save(mapToKiranaTransaction(transactionRequest, conversionFactor));

    }

    private KiranaTransaction mapToKiranaTransaction(TransactionRequest transactionRequest, Double conversionFactor){
       String transactionType = transactionRequest.getTransactionType();
       Double value = transactionRequest.getAmount();
       Double debitAmount =isDebitType(transactionType) ? value:Double.valueOf(0) ;
       Double creditAmount = isCreditType(transactionType)? value: Double.valueOf(0);
        debitAmount *= conversionFactor;
        creditAmount *= conversionFactor;

       return KiranaTransaction.builder()
                .transactionId(UUID.randomUUID())
                .creditAmount(creditAmount)
                .debitAmount(debitAmount)
                .transactionTime(LocalDateTime.now())
                .transactionDetails(transactionRequest.getTransactionDesc())
                .build();

    }

    private boolean isCreditType(String transactionType) {
        return transactionType.equalsIgnoreCase("CREDIT");
    }

    private boolean isDebitType(String transactionType){
        return transactionType.equalsIgnoreCase("DEBIT");
    }
}
