package com.example.integration.dto;


import com.example.integration.model.Transaction;

import java.util.List;

public class TransactionResponse extends BaseResponse{

    private List<Transaction> transactions;

    public TransactionResponse(int code, List<Transaction> transactions) {
        super(code);
        this.transactions = transactions;
    }


    public void setTransactionModel(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public List<Transaction> getTransactionModels() {
        return transactions;
    }
}
