package com.assignment.KiranaService.controller;

import com.assignment.KiranaService.entity.Transaction;
import com.assignment.KiranaService.model.TransactionRequest;
import com.assignment.KiranaService.model.TransactionSummaryDto;
import com.assignment.KiranaService.service.TransactionService;
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
 * REST controller for managing transactions for  a Kirana store Register.
 */
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * Endpoint for recording a new transaction.
     *
     * @param transactionRequest The request containing transaction details.
     * @return ResponseEntity indicating the success of the transaction recording.
     */
    @PostMapping(value = "/record")
    public ResponseEntity<Map<String, Object>> recordTransaction(@RequestBody @Valid TransactionRequest transactionRequest) {
        transactionService.saveTransaction(transactionRequest);
        return ResponseEntity.ok(Map.of("success", "transaction saved"));
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
        List<Transaction> transactions = transactionService.getTransactionsByDate(date);
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
        return ResponseEntity.ok(transactionService.getTransactionSummaryOn(date));
    }
}
