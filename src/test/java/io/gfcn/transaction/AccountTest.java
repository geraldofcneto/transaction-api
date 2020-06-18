package io.gfcn.transaction;

import io.gfcn.transaction.model.Transaction;
import io.gfcn.transaction.resource.TransactionResource;
import io.gfcn.transaction.vo.TransactionRequest;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.gfcn.transaction.model.Account;
import io.gfcn.transaction.repository.AccountRepository;
import io.gfcn.transaction.resource.AccountResource;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@Sql({ "/db/data.sql" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AccountTest {

    // TODO 
    // separate resource tests and repository tests
    // mock dependecies

    @Autowired
    AccountResource resource;

    @Autowired
    AccountRepository repository;

    @Autowired
    TransactionResource transactionResource;

    @Test
    void save() {
        Account a1 = Account.builder().documentNumber(11111111111L).build();

        Account a2 = repository.save(a1);

        Assertions.assertNotNull(a2);
    }

    @Test
    void get() {
        Account a1 = Account.builder().documentNumber(11111111111L).build();

        repository.save(a1);
        
        Account account = resource.findByDocument(11111111111L);

        Assertions.assertNotNull(account);
    }

    @Test
    void getStatement() {
        Account a1 = Account.builder().documentNumber(11111111111L).build();

        var request = new TransactionRequest(a1.getAccountId(), 2L, new BigDecimal("1.23"));
        Transaction transaction = transactionResource.create(request);

        List<Transaction> statement = resource.getStatement(a1);

        assertThat(statement, everyItem(isA(Transaction.class)));
        assertThat(statement, Matchers.<Transaction>hasSize(1));
        assertThat(statement, Matchers.contains(transaction));
    }

}