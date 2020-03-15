package io.gfcn.transaction.resource;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.gfcn.transaction.model.OperationType;
import io.gfcn.transaction.model.Transaction;
import io.gfcn.transaction.repository.OperationTypeRepository;
import io.gfcn.transaction.repository.TransactionRepository;
import io.gfcn.transaction.vo.TransactionRequest;

@RestController
@RequestMapping("/transactions")
public class TransactionResource {

    @Autowired
    TransactionRepository repository;

    @Autowired
    OperationTypeRepository operationTypeRepository;

    @PostMapping
    public Transaction create(@RequestBody TransactionRequest request) {

        Long operationTypeId = request.getOperationTypeId();

        OperationType operationType = operationTypeRepository.findById(operationTypeId)
                .orElseThrow(OperationTypeNotFoundException::new);

        BigDecimal amount = operationType.isInvertedSignal() 
                ? request.getAmount().negate() 
                : request.getAmount();

        Transaction transaction = Transaction.builder()
                .accountId(request.getAccountId())
                .operationTypeId(operationTypeId)
                .amount(amount).build();

        return repository.save(transaction);

    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "OperationType not found")
    public class OperationTypeNotFoundException extends RuntimeException {
      private static final long serialVersionUID = 1L;
    }
}