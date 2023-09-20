package com.example.customerrewardproject.controller;


import com.example.customerrewardproject.exception.CustomerNotFoundException;
import com.example.customerrewardproject.model.Customer;
import com.example.customerrewardproject.model.ErrorResponse;
import com.example.customerrewardproject.model.ResponseMessage;
import com.example.customerrewardproject.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/customer")
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    private static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Customer> getCustomerById(@PathVariable("id") long id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer doesn't exist.");
        }
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> createCustomer(@Validated @RequestBody Customer customer) {
//        logger.debug("create customer", customer);
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(new ResponseMessage("customer has been created", savedCustomer), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") long id, @Validated @RequestBody Customer customer) {
        Customer currentCustomer = customerService.findById(id);
        if (currentCustomer == null) {
            throw new CustomerNotFoundException("Student doesn't exist");
        }

        currentCustomer.setName(customer.getName());
        currentCustomer.setRewardPoints(customer.getRewardPoints());
        currentCustomer.setEmail(customer.getEmail());
        customerService.updateCustomer(currentCustomer);
        return new ResponseEntity<>(currentCustomer, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessage> deleteCustomer(@PathVariable("id") long id) {
        Customer customer = customerService.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        customerService.deleteCustomerById(id);
        return new ResponseEntity<>(new ResponseMessage("Customer has been deleted", customer), HttpStatus.OK);
    }
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorResponse> exceptionHandlerCustomerNotFound(Exception ex){
        logger.error("Can't find customer");
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);
    }

}
