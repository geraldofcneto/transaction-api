package io.gfcn.transaction;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.gfcn.transaction.model.Transaction;
import io.gfcn.transaction.repository.TransactionRepository;
import io.gfcn.transaction.resource.TransactionResource;
import io.gfcn.transaction.vo.TransactionRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql({ "/db/data.sql" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AppTests {

    @Autowired
    TransactionResource resource;

    @Autowired
    TransactionRepository repository;

    @Test
    void contextLoads() {
    }

    @Test
    void createTransaction(){
        var request = new TransactionRequest(1L, 2L, new BigDecimal("1.23"));

        assertNotNull(resource);

        Transaction transaction = resource.create(request);

        assertEquals(1L, transaction.getAccountId());
        assertEquals(2L, transaction.getOperationTypeId());
        assertEquals(new BigDecimal("-1.23"), transaction.getAmount());
        assertNotNull(transaction.getTransactionId());
    }

    @Test
    void saveTransaction(){

        Instant i = Instant.now();

        Transaction t1 = Transaction.builder()
                .accountId(1L)
                .operationTypeId(2L)
                .amount(new BigDecimal("1.23"))
                .eventDate(i)
                .build();

        Transaction t2 = repository.save(t1);

        assertNotNull(t2);
        assertNotNull(t2.getTransactionId());
        assertEquals(i, t2.getEventDate());
        
        Transaction t3 = repository.findById(t2.getTransactionId()).get();

        assertEquals(t2, t3);
    }

}
