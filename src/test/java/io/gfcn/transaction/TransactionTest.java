package io.gfcn.transaction;

import java.math.BigDecimal;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

import io.gfcn.transaction.model.Transaction;
import io.gfcn.transaction.resource.TransactionResource;
import io.gfcn.transaction.vo.TransactionRequest;

@SpringBootTest
@Sql({ "/db/data.sql" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TransactionTest {

    @Autowired
    TransactionResource transactionResource;

    @Test
    void createNegativeTransaction() {
        var request = new TransactionRequest(1L, 2L, new BigDecimal("1.23"));

        Assertions.assertNotNull(transactionResource);

        Transaction transaction = transactionResource.create(request);

        Assertions.assertEquals(1L, transaction.getAccountId());
        Assertions.assertEquals(2L, transaction.getOperationTypeId());
        Assertions.assertEquals(new BigDecimal("-1.23"), transaction.getAmount());
        Assertions.assertNotNull(transaction.getTransactionId());
    }

    @Test
    void createPositiveTransaction() {
        var request = new TransactionRequest(1L, 4L, new BigDecimal("1.23"));

        Assertions.assertNotNull(transactionResource);

        Transaction transaction = transactionResource.create(request);

        Assertions.assertEquals(1L, transaction.getAccountId());
        Assertions.assertEquals(4L, transaction.getOperationTypeId());
        Assertions.assertEquals(new BigDecimal("1.23"), transaction.getAmount());
        Assertions.assertNotNull(transaction.getTransactionId());
    }

    @Test
    void createInvertedTransaction() {
        // Could be useful for transaction rollback, but should be confirmed

        var request = new TransactionRequest(1L, 1L, new BigDecimal("-1.23"));

        Assertions.assertNotNull(transactionResource);

        Transaction transaction = transactionResource.create(request);

        Assertions.assertEquals(1L, transaction.getAccountId());
        Assertions.assertEquals(1L, transaction.getOperationTypeId());
        Assertions.assertEquals(new BigDecimal("1.23"), transaction.getAmount());
        Assertions.assertNotNull(transaction.getTransactionId());
    }
}