package com.example.customerrewardproject.util;

import com.example.customerrewardproject.entity.CustomerEntity;
import com.example.customerrewardproject.model.Customer;

public class CustomerEntityVoConverter {
    public static Customer convertEntityToVo(CustomerEntity entity){
        if(entity==null)return null;
        Customer customer=new Customer();
        customer.setId(entity.getId());
        customer.setName(entity.getName());
        customer.setEmail(entity.getEmail());
        customer.setRewardPoints(entity.getRewardPoints());
        return customer;

    }
    public static CustomerEntity convertVoToEntity(Customer customer){
        if(customer==null)return null;
        CustomerEntity entity=new CustomerEntity();
        entity.setId(customer.getId());
        entity.setName(customer.getName());
        entity.setEmail(customer.getEmail());
        entity.setRewardPoints(customer.getRewardPoints());
        return entity;
    }
}
