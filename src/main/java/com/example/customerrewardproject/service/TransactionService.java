package com.example.customerrewardproject.service;

import com.example.customerrewardproject.model.Transaction;

public interface TransactionService {
    Transaction findById(long id);
    Transaction saveTransaction(Transaction transaction);
    Transaction updateTransaction(Transaction transaction);
    void deleteTransactionById(long id);
}
