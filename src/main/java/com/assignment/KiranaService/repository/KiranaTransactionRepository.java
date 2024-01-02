package com.assignment.KiranaService.repository;

import com.assignment.KiranaService.entity.KiranaTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface KiranaTransactionRepository extends JpaRepository<KiranaTransaction, UUID> {
}
