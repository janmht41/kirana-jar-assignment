package com.assignment.KiranaService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Builder;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "KIRANA_TRANSACTIONS")
public class KiranaTransaction {
    @Id
    @Column(name="TRAN_ID")
    private UUID transactionId;

    @Column(name="DEBIT_AMT_INR", columnDefinition="integer default 0")
    private BigDecimal debitAmount;

    @Column(name="TRAN_DATETIME")
    private LocalDateTime transactionTime;
    @Column(name="CREDIT_AMT_INR", columnDefinition="integer default 0")
    private BigDecimal creditAmount;

    @Column(name="TRAN_DESC")
    private String transactionDetails;

    public KiranaTransaction(UUID transactionId, BigDecimal debitAmount, LocalDateTime transactionTime, BigDecimal creditAmount, String transactionDetails) {
        this.transactionId = transactionId;
        this.debitAmount = debitAmount;
        this.transactionTime = transactionTime;
        this.creditAmount = creditAmount;
        this.transactionDetails = transactionDetails;
    }
 public  KiranaTransaction(){}
}
