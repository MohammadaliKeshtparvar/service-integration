package com.example.integration.infrastructure;

import com.example.integration.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DbContext {
    private final List<Transaction> transactionList;

    public DbContext(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public DbContext() {
        this.transactionList = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        this.transactionList.add(transaction);
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }
}
