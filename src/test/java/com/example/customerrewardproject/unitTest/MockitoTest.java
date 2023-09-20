package com.example.customerrewardproject.unitTest;

import com.example.customerrewardproject.model.Customer;
import com.example.customerrewardproject.model.Transaction;
import com.example.customerrewardproject.service.CustomerService;
import com.example.customerrewardproject.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

public class MockitoTest {

    @Spy
    private CustomerService customerService;
    @Spy
    private TransactionService transactionService;

    @BeforeEach
    public void setupMockito(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testCustomerCreation(){
        System.out.println("test Customer Creation");
        Customer customer=new Customer();
        doReturn(customer).when(customerService).saveCustomer(any());
        System.out.println("test Customer Creation finished");
    }

    @Test
    public void testTransactionCreation(){
        System.out.println("test Transaction Creation");
        Transaction transaction=new Transaction();
        doReturn(transaction).when(transactionService).saveTransaction(any());
        System.out.println("test Transaction Creation finished");
    }

}
