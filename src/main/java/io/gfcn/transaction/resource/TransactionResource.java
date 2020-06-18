package io.gfcn.transaction.resource;

import java.math.BigDecimal;

import io.gfcn.transaction.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.gfcn.transaction.model.Transaction;
import io.gfcn.transaction.vo.TransactionRequest;

@RestController
@RequestMapping("/transactions")
public class TransactionResource {

    @Autowired
    TransactionService transactionService;

    @PostMapping
    public Transaction create(@RequestBody TransactionRequest request) {

        return transactionService.getTransaction(request.getOperationTypeId(), request.getAccountId(), request.getAmount());
    }

    private Transaction getTransaction(Long operationTypeId, Long accountId, BigDecimal amount) {

        return transactionService.getTransaction(operationTypeId, accountId, amount);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "OperationType not found")
    public static class OperationTypeNotFoundException extends RuntimeException {
      private static final long serialVersionUID = 1L;
    }
}