package com.assignment.KiranaService.controller;

import com.assignment.KiranaService.model.TransactionRequest;
import com.assignment.KiranaService.model.TransactionResponse;
import com.assignment.KiranaService.service.HandleTransactionRecord;
import com.assignment.KiranaService.service.HandleTransactionRecordImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private HandleTransactionRecord handleTransactionRecord;

    @PostMapping(value ="/record")
    public ResponseEntity<String> recordTransaction(@RequestBody @Valid TransactionRequest transactionRequest){
        handleTransactionRecord.saveKiranaTransaction(transactionRequest);
        return new ResponseEntity<>(
                "success",
                HttpStatus.OK);
    }

    @GetMapping(value = "/transactions")
    public List<TransactionResponse> getTransactions(@RequestParam(name = "date",required = false)
                                                         @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date)
    {
        return handleTransactionRecord.getTransactions(date);
    }
}
