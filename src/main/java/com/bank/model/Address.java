package com.bank.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {
    private int pincode;
    private String city;
    private String street;
//    private String state;
//    private String country;
    
    Address(String street, String city, int pincode){
        this.street = street;
        this.city = city;
        this.pincode = pincode;
    }
}
