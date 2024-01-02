package com.assignment.KiranaService.repository;

import com.assignment.KiranaService.entity.KiranaTransaction;
import com.assignment.KiranaService.model.TransactionSummaryDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface KiranaTransactionRepository extends JpaRepository<KiranaTransaction, UUID> {


    @Query("SELECT NEW com.assignment.KiranaService.model.TransactionSummaryDto(" +
            "SUM(t.debitAmount), SUM(t.creditAmount), DATE(t.transactionTime)) " +
            "FROM KiranaTransaction t " +
            "WHERE DATE(t.transactionTime) = :date " +
            "GROUP BY DATE(t.transactionTime)")
    List<TransactionSummaryDto> getTransactionSummaryByDate(@Param("date") LocalDate date);


}

