package com.bank.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Account {
    private static long number = 10000;
    private long accountNumber;
    private long aadharNumber;
    private double balance;
    private List<Transaction> transactions;
    
    private long getUniqueNumber() {
        return number++;
    }
    
    public Account(long aadharNumber, double balance){
        this.aadharNumber = aadharNumber;
        this.accountNumber = getUniqueNumber();
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }
    
}
