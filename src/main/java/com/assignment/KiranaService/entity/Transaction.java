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

/**
 * Represents a transaction entity in the Kirana Service.
 * This entity is mapped to the "KIRANA_TRANSACTIONS" table in the database.
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "KIRANA_TRANSACTIONS")
public class Transaction {

    /**
     * Unique identifier for the transaction.
     */
    @Id
    @Column(name="TRAN_ID")
    private UUID transactionId;

    /**
     * Amount debited in INR. Default value is 0.
     */
    @Column(name="DEBIT_AMT_INR", columnDefinition="double default 0")
    private Double debitAmount;

    /**
     * Date and time of the transaction.
     */
    @Column(name="TRAN_DATETIME")
    private LocalDateTime transactionTime;

    /**
     * Amount credited in INR. Default value is 0.
     */
    @Column(name="CREDIT_AMT_INR", columnDefinition="double default 0")
    private Double creditAmount;

    /**
     * Details about the transaction.
     */
    @Column(name="TRAN_DESC")
    private String transactionDetails;

    /**
     * Payment mode used for the transaction.
     */
    @Column(name = "PAYMENT_MODE")
    private String paymentMode;

    /**
     * User who created the transaction.
     */
    @Column(name = "CREATED_BY")
    private String creationUser;
}
