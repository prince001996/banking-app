package com.bank.repository;

import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.model.TransactionType;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountRepository {
    public Map<Long, Account> accounts = new HashMap<>();
    
    public Account getAccountByAccountNumber(long accountNumber) {
        return accounts.get(accountNumber);
    }
    
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accounts.values());
    }
    
    public void addAccount(Account account) {
        accounts.putIfAbsent(account.getAccountNumber(), account);
        return;
    }
    
    public boolean exists(long account) {
        return accounts.containsKey(account);
    }
    
    public void addTransactionToAccount(Transaction transaction) {
        long accountNumber = transaction.getAccountNumber();
        Account account = accounts.get(accountNumber);
        List<Transaction> accountTransactions = account.getTransactions();
        
        if(transaction.getTransactionType() == TransactionType.DEBIT){
            account.setBalance(account.getBalance() - transaction.getTransactionAmount());
        }
        else{
            account.setBalance(account.getBalance() + transaction.getTransactionAmount());
        }
        accountTransactions.add(transaction);
    }
}
