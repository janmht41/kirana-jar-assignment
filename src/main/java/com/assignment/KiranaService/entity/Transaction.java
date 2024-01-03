package com.assignment.KiranaService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "KIRANA_TRANSACTIONS")
public class Transaction {
    @Id
    @Column(name="TRAN_ID")
    private UUID transactionId;

    @Column(name="DEBIT_AMT_INR", columnDefinition="double default 0")
    private Double debitAmount;

    @Column(name="TRAN_DATETIME")
    private LocalDateTime transactionTime;

    @Column(name="CREDIT_AMT_INR", columnDefinition="double default 0")
    private Double creditAmount;

    @Column(name="TRAN_DESC")
    private String transactionDetails;

}
