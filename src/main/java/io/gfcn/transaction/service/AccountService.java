package io.gfcn.transaction.service;

import io.gfcn.transaction.model.Account;
import io.gfcn.transaction.model.Transaction;
import io.gfcn.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    public TransactionRepository transactionRepository;

    public AccountService() {
    }

    public List<Transaction> getStatement(Account account) {
        return transactionRepository.findAllByAccountId(account.getAccountId());
    }
}