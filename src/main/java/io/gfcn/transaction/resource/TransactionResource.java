package io.gfcn.transaction.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.gfcn.transaction.model.Transaction;
import io.gfcn.transaction.repository.TransactionRepository;
import io.gfcn.transaction.vo.TransactionRequest;


@RestController("transactions")
public class TransactionResource {
    
    @Autowired
    TransactionRepository repository;

    @PostMapping()
    public Transaction create(@RequestBody TransactionRequest request) {
        
        Transaction transaction = Transaction.builder()
                .accountId(request.getAccountId())
                .operationTypeId(request.getOperationTypeId())
                .amount(request.getAmount())
                .build();
        
        return repository.save(transaction);

    }
    
    
}