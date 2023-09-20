package com.example.customerrewardproject.controller;

import com.example.customerrewardproject.exception.CustomerNotFoundException;
import com.example.customerrewardproject.exception.TransactionNotFoundException;
import com.example.customerrewardproject.model.Customer;
import com.example.customerrewardproject.model.ErrorResponse;
import com.example.customerrewardproject.model.ResponseMessage;
import com.example.customerrewardproject.model.Transaction;
import com.example.customerrewardproject.service.CustomerService;
import com.example.customerrewardproject.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.example.customerrewardproject.util.RewardPointsCalculator.getPointsBySpent;

import java.util.Date;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {
    private TransactionService transactionService;


    private CustomerService customerService;

    @Autowired
    public TransactionController(TransactionService transactionService, CustomerService customerService) {
        this.transactionService = transactionService;
        this.customerService = customerService;
    }

    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Transaction> getTransactionById(@PathVariable("id") long id) {
        Transaction transaction = transactionService.findById(id);
        if (transaction == null) {
            throw new TransactionNotFoundException("Customer doesn't exist.");
        }
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping("create/{customerId}/{spent}")
    public ResponseEntity<ResponseMessage> createTransaction(@PathVariable("customerId") long customerId, @PathVariable("spent") double spent) {
//        logger.debug("create customer", customer);
        Transaction transaction = new Transaction();
        transaction.setCustomerId(customerId);
        transaction.setTransactionDate(new Date());
        transaction.setRewardPointsChange(getPointsBySpent(spent));

        Customer customer = customerService.findById(customerId);
        customer.setRewardPoints(customer.getRewardPoints() + transaction.getRewardPointsChange());
        Customer savedCustomer = customerService.saveCustomer(customer);

        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(new ResponseMessage("transaction has been created", savedTransaction), HttpStatus.CREATED);
    }


    @PostMapping("update/{id}/{spent}")
    public ResponseEntity<ResponseMessage> updateTransaction(@PathVariable("id") long id, @PathVariable("spent") double spent) {
//        logger.debug("create customer", customer);

        Transaction transaction = transactionService.findById(id);
        Long customerId = transaction.getCustomerId();
        Double prevPoints = transaction.getRewardPointsChange();
        transaction.setRewardPointsChange(getPointsBySpent(spent));

        Customer customer = customerService.findById(customerId);
        customer.setRewardPoints(customer.getRewardPoints() + transaction.getRewardPointsChange() - prevPoints);
        Customer savedCustomer = customerService.saveCustomer(customer);

        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        return new ResponseEntity<>(new ResponseMessage("transaction has been created", savedTransaction), HttpStatus.CREATED);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessage> deleteTransaction(@PathVariable("id") long id) {
        Transaction transaction = transactionService.findById(id);
        if (transaction == null) {
            throw new TransactionNotFoundException("Transaction doesn't exist");
        }
        Double prevPoints = transaction.getRewardPointsChange();
        Long customerId = transaction.getCustomerId();
        Customer customer = customerService.findById(customerId);
        customer.setRewardPoints(customer.getRewardPoints() - prevPoints);
        customerService.saveCustomer(customer);
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(new ResponseMessage("Transaction has been deleted", transaction), HttpStatus.OK);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionHandlerTransactionNotFound(Exception ex) {
        logger.error("Can't find transaction");
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


}
