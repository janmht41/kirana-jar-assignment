package com.assignment.KiranaService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;


@Data
@Builder
@AllArgsConstructor
public class TransactionSummaryDto {
    private Double creditAmount;
    private Double debitAmount;
    private Date transactionDate;
}
