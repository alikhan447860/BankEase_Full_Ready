package com.bankease.controller;

import com.bankease.model.*;
import com.bankease.service.BankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BankingController {

    @Autowired
    private BankingService service;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return service.createUser(user);
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account acc) {
        return service.createAccount(acc);
    }

    @PostMapping("/transfer")
    public Transaction transfer(@RequestParam Long from, @RequestParam Long to, @RequestParam Double amount) throws Exception {
        return service.transfer(from, to, amount);
    }

    @GetMapping("/transactions/{accountId}")
    public List<Transaction> getTransactions(@PathVariable Long accountId) {
        return service.getTransactions(accountId);
    }
}
