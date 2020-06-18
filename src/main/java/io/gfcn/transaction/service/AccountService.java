package io.gfcn.transaction.service;public class AccountService{@org.springframework.beans.factory.annotation.Autowired
    io.gfcn.transaction.repository.TransactionRepository transactionRepository;	public AccountService()	{	}public java.util.List<io.gfcn.transaction.model.Transaction> getStatement(io.gfcn.transaction.model.Account account) {
        return transactionRepository.findAllByAccountId(account.getAccountId());
    }}