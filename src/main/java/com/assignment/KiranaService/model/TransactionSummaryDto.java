package com.assignment.KiranaService.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;


@Data
@AllArgsConstructor
public class TransactionSummaryDto {
    private Double creditAmount;
    private Double debitAmount;

    private Date transactionDate;
}
