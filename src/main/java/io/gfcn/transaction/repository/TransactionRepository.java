package io.gfcn.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.gfcn.transaction.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

  
}