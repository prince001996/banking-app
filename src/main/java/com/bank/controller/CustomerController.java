package com.bank.controller;


import com.bank.model.Customer;
import com.bank.model.CustomerDetail;
import com.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    
    /**
     * Returns list of all Customers in the bank
     * @return List of all customers
     */
    @GetMapping("/customers")
    public ResponseEntity<List> getAllCustomers(){
        return customerService.getAllCustomers();
    }
    
    /**
     * Takes customerId and returns the Customer
     * @param customerId unique value for a customer which is automatically generated
     * @return the customer based on the id
     */
    @GetMapping("/customer/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable long customerId){
        return customerService.getCustomerById(customerId);
    }
    
    /**
     * Takes aadharNumber and returns a Customer
     * @param aadharNumber unique value for a customer
     * @return the customer based on aadharNumber
     */
    @GetMapping("/customer/aadhar/{aadharNumber}")
    public ResponseEntity<Customer> getCustomerByAadharNumber(@PathVariable long aadharNumber){
        return customerService.getCustomerByAadharNumber(aadharNumber);
    }
    
    /**
     * Takes customer Details and adds the customer to bank
     * @param customerDetail details of a customer
     * @return String denoting the customer is added
     */
    @PostMapping("/customer")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDetail customerDetail){
        return customerService.addCustomer(customerDetail);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
