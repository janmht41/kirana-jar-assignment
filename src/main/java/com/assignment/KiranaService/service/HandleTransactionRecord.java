package com.assignment.KiranaService.service;

import com.assignment.KiranaService.entity.KiranaTransaction;
import com.assignment.KiranaService.model.TransactionRequest;

import com.assignment.KiranaService.repository.KiranaTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class HandleTransactionRecord{
    @Autowired
    private  KiranaTransactionRepository kiranaTransactionRepository;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public void saveKiranaTransaction(TransactionRequest transactionRequest){
        System.out.println(webClientBuilder.build()
                .get()
                .uri("https://api.fxratesapi.com/latest?base=USD&currencies=INR")
                .retrieve()
                .bodyToMono(String.class)
                .block());
        KiranaTransaction save = kiranaTransactionRepository.save(mapToKiranaTransaction(transactionRequest));
    }

    private KiranaTransaction mapToKiranaTransaction(TransactionRequest transactionRequest){
       BigDecimal debitAmount =transactionRequest.getTransactionType().equalsIgnoreCase("DEBIT") ? transactionRequest.getAmount():BigDecimal.valueOf(0);
        BigDecimal creditAmount = transactionRequest.getTransactionType().equalsIgnoreCase("CREDIT") ? transactionRequest.getAmount() : BigDecimal.valueOf(0);
        return new KiranaTransaction(
                UUID.randomUUID(),
                debitAmount,
                LocalDateTime.now(),
                creditAmount,
                transactionRequest.getTransactionDesc()
        );

//       return KiranaTransaction.builder()
//                .transactionId(UUID.randomUUID())
//                .creditAmount(creditAmount)
//                .debitAmount(debitAmount)
//                .transactionTime(LocalDateTime.now())
//                .transactionDetails(transactionRequest.getTransactionDesc())
//                .build();
    }
}
