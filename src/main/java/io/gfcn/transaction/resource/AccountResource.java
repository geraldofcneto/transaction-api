package io.gfcn.transaction.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.gfcn.transaction.model.Account;
import io.gfcn.transaction.repository.AccountRepository;
import io.gfcn.transaction.vo.AccountRequest;

@RestController
@RequestMapping("/accounts")
public class AccountResource {

    @Autowired
    AccountRepository repository;

    @PostMapping
    public Account create(@RequestBody AccountRequest request) {

        Account account = Account.builder().documentNumber(request.getDocumentNumber()).build();

        return repository.save(account);
    }

    @GetMapping("/{documentNumber}")
    public Account findByDocument(@PathVariable Long documentNumber) {

        Account a = Account.builder().documentNumber(documentNumber).build();

        Example<Account> example = Example.of(a);
        
        return repository.findOne(example)
            .orElseThrow(AccountNotFoundException::new);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Account not found")
    public class AccountNotFoundException extends RuntimeException {
      private static final long serialVersionUID = 1L;
    }

}