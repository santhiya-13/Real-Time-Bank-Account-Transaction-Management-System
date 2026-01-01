package com.example.bank.service;

import com.example.bank.model.BankAccount;
import com.example.bank.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository repository;

    // Create Account with validation
    public BankAccount createAccount(BankAccount account) {
        if (account.getBalance() < 1000) {
            throw new RuntimeException("Initial balance must be at least ₹1000");
        }
        if (account.getBalance() < 0) {
            throw new RuntimeException("Balance cannot be negative");
        }
        account.setStatus("ACTIVE");
        account.setCreatedAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        return repository.save(account);
    }

    // Deposit
    public Map<String, Object> deposit(Long id, double amount) {
        BankAccount account = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        account.setBalance(account.getBalance() + amount);
        updateTransactionDetails(account, "DEPOSIT", amount);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Deposit successful");
        response.put("accountId", id);
        response.put("balance", account.getBalance());
        response.put("lastTransactionType", "DEPOSIT");
        response.put("lastTransactionAmount", amount);
        return response;
    }

    // Withdraw
    public Map<String, Object> withdraw(Long id, double amount) {
        BankAccount account = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() - amount < 0) {
            throw new RuntimeException("Insufficient balance - cannot go negative");
        }

        account.setBalance(account.getBalance() - amount);
        updateTransactionDetails(account, "WITHDRAW", amount);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Withdrawal successful");
        response.put("accountId", id);
        response.put("balance", account.getBalance());
        response.put("lastTransactionType", "WITHDRAW");
        response.put("lastTransactionAmount", amount);
        return response;
    }

    // Common method for updating transaction details
    private void updateTransactionDetails(BankAccount account, String type, double amount) {
        account.setLastTransactionType(type);
        account.setLastTransactionAmount(amount);
        account.setLastTransactionAt(LocalDateTime.now());
        account.setUpdatedAt(LocalDateTime.now());
        repository.save(account);
    }

    // Balance Inquiry (Get by ID)
    public BankAccount getAccountById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }

    // Get All Accounts
    public List<BankAccount> getAllAccounts() {
        return repository.findAll();
    }
}