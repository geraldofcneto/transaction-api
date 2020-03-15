package io.gfcn.transaction;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.gfcn.transaction.model.Account;
import io.gfcn.transaction.repository.AccountRepository;
import io.gfcn.transaction.resource.AccountResource;

@SpringBootTest
public class AccountTest {

    // TODO 
    // separate resource tests and repository tests
    // mock dependecies

    @Autowired
    AccountResource resource;

    @Autowired
    AccountRepository repository;

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

}