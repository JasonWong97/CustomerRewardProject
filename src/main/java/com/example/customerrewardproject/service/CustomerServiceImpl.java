package com.example.customerrewardproject.service;

import com.example.customerrewardproject.dao.CustomerRepository;
import com.example.customerrewardproject.entity.CustomerEntity;
import com.example.customerrewardproject.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.customerrewardproject.util.CustomerEntityVoConverter.convertEntityToVo;
import static com.example.customerrewardproject.util.CustomerEntityVoConverter.convertVoToEntity;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public Customer findById(long id) {
        CustomerEntity customerEntity = customerRepository.findById(id).orElse(null);
        return convertEntityToVo(customerEntity);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        CustomerEntity customerEntity=customerRepository.saveAndFlush(convertVoToEntity(customer));
        return convertEntityToVo(customerEntity);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        CustomerEntity customerEntity=customerRepository.saveAndFlush(convertVoToEntity(customer));
        return convertEntityToVo(customerEntity);
    }

    @Override
    public void deleteCustomerById(long id) {
        customerRepository.deleteById(id);
    }


}
