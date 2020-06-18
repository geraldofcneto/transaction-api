package io.gfcn.transaction.service;

import io.gfcn.transaction.model.Account;
import io.gfcn.transaction.model.OperationType;
import io.gfcn.transaction.model.Transaction;
import io.gfcn.transaction.repository.OperationTypeRepository;
import io.gfcn.transaction.repository.TransactionRepository;
import io.gfcn.transaction.resource.TransactionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    public
    TransactionRepository repository;
    @Autowired
    public
    OperationTypeRepository operationTypeRepository;

    public Transaction getTransaction(Long operationTypeId, Long accountId, BigDecimal amount) {
        OperationType operationType = operationTypeRepository.findById(operationTypeId)
                .orElseThrow(TransactionResource.OperationTypeNotFoundException::new);

        BigDecimal amount2 = operationType.isInvertedSignal()
                ? amount.negate()
                : amount;

        Transaction transaction = Transaction.builder()
                .accountId(accountId)
                .operationTypeId(operationTypeId)
                .amount(amount2).build();

        return repository.save(transaction);
    }

    public List<Transaction> getStatement(Account account) {
        return repository.findAllByAccountId(account.getAccountId());
    }
}