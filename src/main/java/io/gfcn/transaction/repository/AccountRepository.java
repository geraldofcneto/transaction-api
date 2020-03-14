package io.gfcn.transaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.gfcn.transaction.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

  
}