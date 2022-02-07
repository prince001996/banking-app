package com.bank.repository;

import com.bank.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TransactionRepository {
    public Map<Long, Transaction> transactions = new HashMap<>();
    
    public Transaction findById(long id){
        return transactions.get(id);
    }
    
    public List<Transaction> findAll(){
        return new ArrayList<>(transactions.values());
    }
    
    public void add(Transaction transaction){
        transactions.putIfAbsent(transaction.getTarnsactionId(), transaction);
    }
}
