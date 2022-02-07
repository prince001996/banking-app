package com.bank.service;


import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.model.TransactionType;
import com.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountService accountService;
    
    public ResponseEntity<Transaction> getTransactionById(long id) {
        Transaction transaction = transactionRepository.findById(id);
        ResponseEntity<Transaction> responseEntity;
        if(transaction == null){
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            responseEntity = new ResponseEntity<>(transaction, HttpStatus.OK);
        }
        return responseEntity;
    }
    
    public ResponseEntity<List> getAllTransactions() {
        return new ResponseEntity<>(transactionRepository.findAll(), HttpStatus.OK);
    }
    
    public ResponseEntity<String> transact(long fromAccount, long toAccount, double amount){
        ResponseEntity<String> responseEntity;
        String responseBody;
        if(!accountService.accountExists(fromAccount)){
            Transaction transaction = new Transaction(fromAccount, amount, TransactionType.FAILURE_WRONG_SENDER_ACCOUNT);
            transactionRepository.add(transaction);
            responseBody = "sender account doesn't exist";
            responseEntity = new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
            return responseEntity;
        }
        else if(!accountService.accountExists(toAccount)){
            Transaction transaction = new Transaction(toAccount, amount, TransactionType.FAILURE_WRONG_RECIEVER_ACCOUNT);
            transactionRepository.add(transaction);
            responseBody = "reciever acount doesn't exist";
            responseEntity = new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
            return responseEntity;
        }
        else{
            if (transferFromAccount(fromAccount, amount)) {
                transferToAccount(toAccount, amount);
                responseBody = "Transaction successfull";
                responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
                return responseEntity;
            } else {
                Transaction transaction = new Transaction(fromAccount, amount, TransactionType.FAILURE_LOW_BALANCE);
                transactionRepository.add(transaction);
                accountService.addTransactionTOAccount(transaction);
                responseBody = "sender doesn't have enough balance!!";
                responseEntity = new ResponseEntity<>(responseBody, HttpStatus.OK);
                return responseEntity;
            }
        }
    }
    
    public boolean transferFromAccount(long fromAccount, double amount) {
        Transaction transaction = new Transaction(fromAccount, amount, TransactionType.DEBIT);
        ResponseEntity<Account> account = accountService.getAccountByAccountNumber(fromAccount);
        if(account.getBody().getBalance() >= amount){
            accountService.addTransactionTOAccount(transaction);
            transactionRepository.add(transaction);
            return true;
        }
        return false;
    }
    public void transferToAccount(long toAccount, double amount) {
        Transaction transaction = new Transaction(toAccount, amount, TransactionType.CREDIT);
        accountService.addTransactionTOAccount(transaction);
        transactionRepository.add(transaction);
        return;

    }
    
    public ResponseEntity<List> getAllTransactionsByAccount(long accountNumber) {
        List<Transaction> transactions = accountService.getAllTransactions(accountNumber);
        ResponseEntity<List> responseEntity;
        if(transactions == null){
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            responseEntity = new ResponseEntity<>(transactions, HttpStatus.OK);
        }
        return  responseEntity;
    }
}
