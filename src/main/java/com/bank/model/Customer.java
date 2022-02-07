package com.bank.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Customer {
    private static long id = 1;
    private long customerId;
    private CustomerDetail customerDetail;
    private List<Account> accounts;
    
    private long getUniqueId(){
        return id++;
    }
    
    public Customer(CustomerDetail customerDetail){
        this.customerId = getUniqueId();
        this.customerDetail = customerDetail;
        this.accounts = new ArrayList<>();
    }
}
