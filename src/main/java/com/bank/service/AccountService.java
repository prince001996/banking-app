package com.bank.service;

import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.model.Transaction;
import com.bank.repository.AccountRepository;
import com.bank.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;
    
    
    
    public ResponseEntity<Account> getAccountByAccountNumber(long accountNumber) {
        Account account = accountRepository.getAccountByAccountNumber(accountNumber);
        ResponseEntity<Account> responseEntity;
        if(account == null){
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            responseEntity = new ResponseEntity<>(account, HttpStatus.OK);
        }
        return responseEntity;
    }
    
    public ResponseEntity<List> getAccountsByAadharNumber(long aadarNumber) {
        ResponseEntity<Customer> customer = customerService.getCustomerByAadharNumber(aadarNumber);
        ResponseEntity<List> responseEntity;
        if(customer.getStatusCode() == HttpStatus.NOT_FOUND){
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            responseEntity = new ResponseEntity<>(customer.getBody().getAccounts(), HttpStatus.OK);
        }
        return responseEntity;
    }
    
    public ResponseEntity<List> getAllAccounts() {
        List<Account> accounts = accountRepository.getAllAccounts();
        ResponseEntity<List> responseEntity = new ResponseEntity<>(accounts, HttpStatus.OK);
        return responseEntity;
    }
    
    public ResponseEntity<String> createAndassignAccount(long aadharNumber, double amount) {
        ResponseEntity<String> responseEntity;
        if(customerRepository.exists(aadharNumber)){
            Account account = new Account(aadharNumber, amount);
            accountRepository.addAccount(account);
            customerService.assignAccountToUser(account);
            
            String responseBody = String.format(
                    "The account was created for customer with aadharNumber %d."
                    +"The account number is %d and the amount is %f",
                    aadharNumber, account.getAccountNumber(), account.getBalance()
            );
            responseEntity = new ResponseEntity<>(responseBody, HttpStatus.CREATED);
        }
        else{
            responseEntity = new ResponseEntity<>("Please add/create the customer first", HttpStatus.METHOD_NOT_ALLOWED);
        }
        return responseEntity;
    }
    
    public ResponseEntity<String> getBalanceByAccount(long accountNumber) {
        Account account =  accountRepository.getAccountByAccountNumber(accountNumber);
        ResponseEntity<String> responseEntity;
        if(account == null){
            responseEntity = new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        }
        else{
            responseEntity = new ResponseEntity<>(String.format("The balance in account %d is : %d",accountNumber, account.getBalance()), HttpStatus.OK);
        }
        return responseEntity;
    }
    
    public boolean accountExists(long accountNumber) {
        return accountRepository.exists(accountNumber);
    }
    
    public void addTransactionTOAccount(Transaction transaction) {
        accountRepository.addTransactionToAccount(transaction);
        return;
    }
    
    public List<Transaction> getAllTransactions(long accountNumber) {
        Account account = accountRepository.getAccountByAccountNumber(accountNumber);
        return account.getTransactions();
    }
}
