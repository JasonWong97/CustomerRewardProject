package com.example.customerrewardproject.service;

import com.example.customerrewardproject.model.Customer;

public interface CustomerService {
    Customer findById(long id);
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    void deleteCustomerById(long id);
}
