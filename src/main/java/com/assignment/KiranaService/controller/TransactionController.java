package com.assignment.KiranaService.controller;

import com.assignment.KiranaService.model.TransactionRequest;
import com.assignment.KiranaService.service.HandleTransactionRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    private HandleTransactionRecord handleTransactionRecord;

    @PostMapping(value ="/record")
    public ResponseEntity<String> recordTransaction(@RequestBody TransactionRequest transactionRequest){
        handleTransactionRecord.saveKiranaTransaction(transactionRequest);
        return new ResponseEntity<>(
                "success",
                HttpStatus.OK);
    }
}
