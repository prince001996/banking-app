package com.bank.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDetail {
    private long aadharNumber;
    private long pan;
    private String name;
    private Address address;
    
    public CustomerDetail(long aadharNumber, long pan, String name,  String street, String city, int pincode){
        this.aadharNumber = aadharNumber;
        this.pan = pan;
        this.name = name;
        Address address = new Address(street, city, pincode);
        this.address = address;
    }
}
