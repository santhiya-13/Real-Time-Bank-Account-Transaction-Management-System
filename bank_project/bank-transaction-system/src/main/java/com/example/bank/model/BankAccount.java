package com.example.bank.model;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bank_account")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double balance;
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(name = "last_transaction_at")
    private LocalDateTime lastTransactionAt;

    @Column(name = "last_transaction_type")
    private String lastTransactionType;

    @Column(name = "last_transaction_amount")
    private Double lastTransactionAmount;

    @Column(name = "account_active")
    private boolean accountActive = true;

    // Constructors
    public BankAccount() {}

    public BankAccount(String name, double balance, String status) {
        this.name = name;
        this.balance = balance;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }

    public LocalDateTime getLastTransactionAt() { return lastTransactionAt; }
    public void setLastTransactionAt(LocalDateTime lastTransactionAt) { this.lastTransactionAt = lastTransactionAt; }

    public String getLastTransactionType() { return lastTransactionType; }
    public void setLastTransactionType(String lastTransactionType) { this.lastTransactionType = lastTransactionType; }

    public Double getLastTransactionAmount() { return lastTransactionAmount; }
    public void setLastTransactionAmount(Double lastTransactionAmount) { this.lastTransactionAmount = lastTransactionAmount; }

    public boolean isAccountActive() { return accountActive; }
    public void setAccountActive(boolean accountActive) { this.accountActive = accountActive; }
}
