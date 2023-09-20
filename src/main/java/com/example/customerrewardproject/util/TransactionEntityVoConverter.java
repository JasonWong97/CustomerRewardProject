package com.example.customerrewardproject.util;

import com.example.customerrewardproject.entity.CustomerEntity;
import com.example.customerrewardproject.entity.TransactionEntity;
import com.example.customerrewardproject.model.Customer;
import com.example.customerrewardproject.model.Transaction;

public class TransactionEntityVoConverter {
    public static Transaction convertEntityToVo(TransactionEntity entity){
        if(entity==null)return null;
        Transaction transaction=new Transaction();
        transaction.setId(entity.getId());
        transaction.setCustomerId(entity.getCustomerId());
        transaction.setRewardPointsChange(entity.getRewardPointsChange());
        transaction.setTransactionDate(entity.getTransactionDate());
        return transaction;

    }
    public static TransactionEntity convertVoToEntity(Transaction transaction){
        if(transaction==null)return null;
        TransactionEntity entity=new TransactionEntity();
        entity.setId(transaction.getId());
        entity.setCustomerId(transaction.getCustomerId());
        entity.setRewardPointsChange(transaction.getRewardPointsChange());
        entity.setTransactionDate(transaction.getTransactionDate());
        return entity;
    }
}
