package com.example.demo.controller;

import com.example.demo.model.BankAccount;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService service;

    // Create or update an account
    @PostMapping
    public BankAccount saveAccount(@RequestBody BankAccount account) {
        return service.saveAccount(account);
    }

    // Get all accounts
    @GetMapping
    public List<Account> getAllAccounts() {
        return service.getAllAccounts();
    }

    // Debit amount
    @PostMapping("/{id}/debit")
    public Account debit(@PathVariable Long id, @RequestParam double amount) {
        return service.debit(id, amount);
    }

    // Credit amount
    @PostMapping("/{id}/credit")
    public Account credit(@PathVariable Long id, @RequestParam double amount) {
        return service.credit(id, amount);
    }

    // Transfer amount
    @PostMapping("/transfer")
    public void transfer(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam double amount) {
        service.transfer(fromAccountId, toAccountId, amount);
    }
}
