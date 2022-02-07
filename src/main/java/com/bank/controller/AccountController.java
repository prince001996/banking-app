package com.bank.controller;


import com.bank.model.Account;
import com.bank.service.AccountService;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
public class AccountController {

    @Autowired
    AccountService accountService;
    
    /**
     * Returns account information based on account number
     * @param accountNumber unique for an account, assigned at time of account creation
     * @return account information based on accountNumber
     */
    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<Account> getAccountByAccountNumber(@PathVariable long accountNumber){
        return accountService.getAccountByAccountNumber(accountNumber);
    }
    
    /**
     * Returns list of accounts for a customer based on aadhar number
     * @param aadarNumber unique for a customer
     * @return list of accounts based on aadarNumber
     */
    @GetMapping("/accounts/aadharNumber/{aadarNumber}")
    public ResponseEntity<List> getAccountsByAadharNumber(@PathVariable long aadarNumber){
        return accountService.getAccountsByAadharNumber(aadarNumber);
    }
    
    /**
     * Returns all accounts in the bank
     * @return list of accounts in the bank
     */
    @GetMapping("/accounts")
    public ResponseEntity<List> getAllAccount(){
        return accountService.getAllAccounts();
    }
    
    /**
     * Returns account balance using account number
     * @param accountNumber tells teh account for which balance is to returned
     * @return the balance in the account
     */
    @GetMapping("/account/balance/{accountNumber}")
    public ResponseEntity<String> getBalanceByAccount(@PathVariable long accountNumber){
        return accountService.getBalanceByAccount(accountNumber);
    }
    
    /**
     * creates and assigns an account if the user already exists based on aadhar number with initial deposit amount
     * @param objectNode contains the values sent in the request body
     * @return message telling if the account was created or not
     */
    @PostMapping("/createAccount")
    public ResponseEntity<String> createAndassignAccount(@RequestBody ObjectNode objectNode){
        return accountService.createAndassignAccount(Long.valueOf(objectNode.get("aadharNumber").toString()), Double.valueOf(objectNode.get("amount").toString()));
    }
    
    /**
     * creates and assigns an account if the user already exists based on aadhar number with initial deposit amount
     * @param aadharNumber aadhar number of teh user for whom account is to be created
     * @param amount deposit amount to be deposited in account after creation
     * @return message telling if the account was created or not
     */
    @PostMapping("/createAccount/{aadharNumber}/{balance}")
    public ResponseEntity<String> createAndassignAccount(@PathVariable long aadharNumber, @PathVariable double amount){
        return accountService.createAndassignAccount(aadharNumber, amount);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
