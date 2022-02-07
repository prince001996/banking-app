package com.bank.repository;

import com.bank.model.Account;
import com.bank.model.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class CustomerRepository {
    public HashMap<Long, Customer> customers = new HashMap<>();
    public HashMap<Long, Long> aadharNumberToCustomerId = new HashMap<>();
    
    public Customer findById(long customerId) {
        return customers.get(customerId);
    }
    
    public Customer findByAadharNumber(long aadharNumber) {
        Long customerId = aadharNumberToCustomerId.get(aadharNumber);
        if(customerId == null){
            return null;
        }
        return customers.get(customerId);
    }
    
    public List<Customer> getAll() {
        return new ArrayList<>(customers.values());
    }
    
    public void add(Customer customer) {
        customers.putIfAbsent(customer.getCustomerId(), customer);
        aadharNumberToCustomerId.put(customer.getCustomerDetail().getAadharNumber(), customer.getCustomerId());
    }
    
    
    public boolean exists(long aadharNumber) {
        return aadharNumberToCustomerId.containsKey(aadharNumber);
    }
    
    public void addAcountToCustomer(Account account) {
        long aadharNumber = account.getAadharNumber();
        long customerId = aadharNumberToCustomerId.get(aadharNumber);
        Customer customer = customers.get(customerId);
        List<Account> customerAccounts = customer.getAccounts();
        if(customerAccounts == null){
            customerAccounts = new ArrayList<>();
        }
        customerAccounts.add(account);
        return;
    }
}
