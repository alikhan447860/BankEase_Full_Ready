package com.bankease.service;

import com.bankease.model.*;
import com.bankease.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BankingService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private AccountRepository accRepo;
    @Autowired
    private TransactionRepository txnRepo;

    public User createUser(User user) {
        return userRepo.save(user);
    }

    public Account createAccount(Account account) {
        return accRepo.save(account);
    }

    public Transaction transfer(Long fromId, Long toId, Double amount) throws Exception {
        Account from = accRepo.findById(fromId).orElseThrow();
        Account to = accRepo.findById(toId).orElseThrow();

        if (from.getBalance() < amount) throw new Exception("Insufficient balance");

        from.setBalance(from.getBalance() - amount);
        to.setBalance(to.getBalance() + amount);

        accRepo.save(from);
        accRepo.save(to);

        Transaction txn = new Transaction(null, fromId, toId, amount, LocalDateTime.now());
        return txnRepo.save(txn);
    }

    public List<Transaction> getTransactions(Long accountId) {
        return txnRepo.findByFromAccountIdOrToAccountId(accountId, accountId);
    }
}
