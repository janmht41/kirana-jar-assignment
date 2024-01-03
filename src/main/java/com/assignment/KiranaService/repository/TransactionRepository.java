package com.assignment.KiranaService.repository;

import com.assignment.KiranaService.entity.Transaction;
import com.assignment.KiranaService.model.TransactionSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findAllByTransactionTimeBetween(LocalDateTime startDate, LocalDateTime endDate);
    @Query("SELECT NEW com.assignment.KiranaService.model.TransactionSummaryDto(" +
            "SUM(t.creditAmount), SUM(t.debitAmount), DATE(t.transactionTime)) " +
            "FROM Transaction t " +
            "WHERE DATE(t.transactionTime) = :date " +
            "GROUP BY DATE(t.transactionTime)")
    TransactionSummaryDto getTransactionSummary(@Param("date") LocalDate date);

}

