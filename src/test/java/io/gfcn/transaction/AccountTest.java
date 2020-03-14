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

    @Autowired
    AccountResource resource;

    @Autowired
    AccountRepository repository;

    @Test
    void save() {
        Account a1 = Account.builder()
                .documentNumber(11111111111L)
                .build();
        
        Account a2 = repository.save(a1);

        Assertions.assertNotNull(a2);
    }
    

}