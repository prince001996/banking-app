package com.bank.service;

import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.CustomerDetail;
import com.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    
    public ResponseEntity<Customer> getCustomerById(long customerId) {
        Customer customer = customerRepository.findById(customerId);
        ResponseEntity<Customer> responseEntity;
        if(customer == null){
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            responseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return responseEntity;
    }
    
    public ResponseEntity<Customer> getCustomerByAadharNumber(long aadharNumber) {
        Customer customer = customerRepository.findByAadharNumber(aadharNumber);
        ResponseEntity<Customer> responseEntity;
        if(customer == null){
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            responseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
        }
        return responseEntity;
    }
    
    public ResponseEntity<List> getAllCustomers() {
        List<Customer> customers = customerRepository.getAll();
        ResponseEntity<List> responseEntity = new ResponseEntity<>(customers, HttpStatus.OK);
        return responseEntity;
    }
    
    public ResponseEntity<String> addCustomer(CustomerDetail customerDetail) {
        Customer customer = new Customer(customerDetail);
        customerRepository.add(customer);
        ResponseEntity<String> responseEntity = new ResponseEntity<>("Customer succefully added to bank.", HttpStatus.CREATED);
        return responseEntity;
    }
    
    
    public void assignAccountToUser(Account account) {
        customerRepository.addAcountToCustomer(account);
    }
    
    
    public boolean CustomerExists(long aadharNumber) {
        return customerRepository.exists(aadharNumber);
    }
}
