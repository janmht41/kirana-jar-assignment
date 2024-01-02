package com.assignment.KiranaService.model;

import java.sql.Date;


public class TransactionSummaryDto {

    public Double getCreditAmount() {
        return creditAmount;
    }

    public Double getDebitAmount() {
        return debitAmount;
    }


    public Date getTransactionDate() {
        return transactionDate;
    }

    public TransactionSummaryDto(Double creditAmount, Double debitAmount, Date transactionDate) {
        this.creditAmount = creditAmount;
        this.debitAmount = debitAmount;
        this.transactionDate = transactionDate;
    }

    private Double creditAmount;
    private Double debitAmount;

    private Date transactionDate;
}
