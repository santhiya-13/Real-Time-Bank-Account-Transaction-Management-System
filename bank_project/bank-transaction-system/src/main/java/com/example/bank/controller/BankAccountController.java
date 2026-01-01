package com.example.bank.controller;

import com.example.bank.model.BankAccount;
import com.example.bank.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {

    @Autowired
    private BankAccountService service;

    // Create Account - POST /accounts
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody BankAccount account) {
        try {
            BankAccount saved = service.createAccount(account);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Deposit - POST /accounts/{id}/deposit?amount=2000
    @PostMapping("/{id}/deposit")
    public ResponseEntity<Map<String, Object>> deposit(@PathVariable Long id,
                                                       @RequestParam double amount) {
        try {
            return ResponseEntity.ok(service.deposit(id, amount));
        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Withdraw - POST /accounts/{id}/withdraw?amount=1000
    @PostMapping("/{id}/withdraw")
    public ResponseEntity<Map<String, Object>> withdraw(@PathVariable Long id,
                                                       @RequestParam double amount) {
        try {
            return ResponseEntity.ok(service.withdraw(id, amount));
        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }

    // Balance Inquiry - GET /accounts/{id}
    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> getAccount(@PathVariable Long id) {
        return ResponseEntity.ok(service.getAccountById(id));
    }

    // Get All Accounts - GET /accounts
    @GetMapping
    public List<BankAccount> getAllAccounts() {
        return service.getAllAccounts();
    }
}