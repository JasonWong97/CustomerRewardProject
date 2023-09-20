package com.example.customerrewardproject.service;

import com.example.customerrewardproject.dao.TransactionRepository;
import com.example.customerrewardproject.entity.CustomerEntity;
import com.example.customerrewardproject.entity.TransactionEntity;
import com.example.customerrewardproject.model.Transaction;
import com.example.customerrewardproject.util.CustomerEntityVoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.customerrewardproject.util.TransactionEntityVoConverter.convertEntityToVo;
import static com.example.customerrewardproject.util.TransactionEntityVoConverter.convertVoToEntity;

@Service("transactionService")
public class TransactionServiceImpl implements TransactionService{


    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public Transaction findById(long id) {
        TransactionEntity transactionEntity=transactionRepository.findById(id).orElse(null);
        return convertEntityToVo(transactionEntity);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        TransactionEntity transactionEntity=transactionRepository.saveAndFlush(convertVoToEntity(transaction));
        return convertEntityToVo(transactionEntity);
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        TransactionEntity transactionEntity=transactionRepository.saveAndFlush(convertVoToEntity(transaction));
        return convertEntityToVo(transactionEntity);
    }

    @Override
    public void deleteTransactionById(long id) {
        transactionRepository.deleteById(id);
    }
}
