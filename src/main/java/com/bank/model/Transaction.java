package com.bank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Transaction {
    private static long id = 0;
    private long tarnsactionId;
    private double transactionAmount;
    private TransactionType transactionType;
    private long accountNumber;
    
    private long getUniqueTransactionId(){
        return id++;
    }
    
    public Transaction(long accountNumber, double transactionAmount, TransactionType transactionType){
        this.tarnsactionId = getUniqueTransactionId();
        this.accountNumber = accountNumber;
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;
    }
    
}
