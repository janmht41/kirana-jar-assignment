package com.assignment.KiranaService.controller;

import com.assignment.KiranaService.entity.Transaction;
import com.assignment.KiranaService.model.TransactionRequestModel;
import com.assignment.KiranaService.model.TransactionSummaryDto;
import com.assignment.KiranaService.service.ITransactionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * REST controller for managing transactions for a Kirana store Register.
 */
@RestController
public class TransactionController {

    @Autowired
    private ITransactionService ITransactionService;

    /**
     * Endpoint for recording a new transaction.
     *
     * @param transactionRequestModel The request containing transaction details.
     * @return ResponseEntity indicating the success of the transaction recording with the Transaction ID.
     */
    @PostMapping(value = "/record")
    public ResponseEntity<Map<String, Object>> recordTransaction(@RequestBody @Valid TransactionRequestModel transactionRequestModel) {
       var transactionId =  ITransactionService.saveTransaction(transactionRequestModel);
        return ResponseEntity.ok(Map.of("Transaction ID: ", transactionId));

    }

    /**
     * Endpoint for retrieving transactions based on the provided date.
     *
     * @param date The date for which transactions are requested.
     * @return ResponseEntity containing a list of transactions for the given date.
     * If not provided, returns transactions for current Date
     */
    @GetMapping(value = "/transactions")
    public ResponseEntity<List<Transaction>> getTransactions(
            @RequestParam(name = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Valid LocalDate date) {
        List<Transaction> transactions = ITransactionService.getTransactionsByDate(date);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving a summary of transactions for a given date.
     *
     * @param date The date for which the transaction summary is requested.
     * @return ResponseEntity containing the transaction summary for the given date.
     * If not provided, returns summary for current Date
     */
    @GetMapping(value = "/summary")
    public ResponseEntity<TransactionSummaryDto> getSummary(
            @RequestParam(name = "date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @Valid LocalDate date) {
        return ResponseEntity.ok(ITransactionService.getTransactionSummaryOn(date));
    }
}
