package io.gfcn.transaction.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
        
        Account account = Account.builder()
                .documentNumber(request.getDocumentNumber()).build();
        
        return repository.save(account);
    }

    @GetMapping("/{documentNumber}")
    public Account getMethodName(@PathVariable Long documentNumber) {
        
        return repository.getOne(documentNumber);
    }
    
    
    
}