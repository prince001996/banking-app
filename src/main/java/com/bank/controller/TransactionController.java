package com.bank.controller;


import com.bank.model.Transaction;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @Autowired
    AccountService accountService;
    
    /**
     * returns transaction details based on its id
     * @param id transaction id of the transaction
     * @return transaction details based on the id
     */
    @GetMapping("/transaction/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable long id){
        return transactionService.getTransactionById(id);
    }
    
    /**
     * Returns a list of all transactions
     * @return list of all teh transactions done in the bank
     */
    @GetMapping("/transactions")
    public ResponseEntity<List> getAllTransactions(){
        return transactionService.getAllTransactions();
    }
    
    /**
     * Retirns a list of transactions done by a user in an account
     * @param accountNumber account number for which all transactions has to be returned
     * @return a list of all transactions of an account
     */
    @GetMapping("/transactions/account/{accountNumber}")
    public ResponseEntity<List> getAllTransactionsByAccount(@PathVariable long accountNumber){
        return transactionService.getAllTransactionsByAccount(accountNumber);
    }
    
    /**
     * does a trnascaction from one account to another if the balance in sender's account is more than the transaction amount
     * @param fromAccount senders account number
     * @param toAccount recievers account number
     * @param amount amount to be transacted
     * @return message telling if teh tarnsaction was successfull or not
     */
    @PostMapping("/transaction/{fromAccount}/{toAccount}/{amount}")
    public ResponseEntity<String> transferFromAccount(
            @PathVariable long fromAccount, @PathVariable long toAccount, @PathVariable double amount){
        return transactionService.transact(fromAccount, toAccount, amount);
    }
    
    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Exception> handleAllExceptions(RuntimeException ex) {
        return new ResponseEntity<Exception>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
