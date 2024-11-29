package com.example.demo.service;
import com.example.demo.model.BankAccount;
import com.example.demo.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository repository;

    // Create or update an account
    public BankAccount saveAccount(BankAccount account) {
        return repository.save(account);
    }

    // Get all accounts
    public List<BankAccount> getAllAccounts() {
        return repository.findAll();
    }

    // Get account by ID
    public BankAccount getAccountById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Account not found"));
    }

    // Debit amount
    public BankAccount debit(Long id, double amount) {
        BankAccount account = getAccountById(id);
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient funds");
        }
        account.setBalance(account.getBalance() - amount);
        return repository.save(account);
    }

    // Credit amount
    public BankAccount credit(Long id, double amount) {
        BankAccount account = getAccountById(id);
        account.setBalance(account.getBalance() + amount);
        return repository.save(account);
    }

    // Transfer amount
    public void transfer(Long fromAccountId, Long toAccountId, double amount) {
        debit(fromAccountId, amount);
        credit(toAccountId, amount);
    }
}
