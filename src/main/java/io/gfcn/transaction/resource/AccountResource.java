package io.gfcn.transaction.resource;

import io.gfcn.transaction.model.Account;
import io.gfcn.transaction.model.Transaction;
import io.gfcn.transaction.repository.AccountRepository;
import io.gfcn.transaction.service.AccountService;
import io.gfcn.transaction.vo.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountResource {

    @Autowired
    AccountService accountService;

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

    public List<Transaction> getStatement(Account account) {
        return accountService.getStatement(account);
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Account not found")
    public class AccountNotFoundException extends RuntimeException {
      private static final long serialVersionUID = 1L;
    }

}