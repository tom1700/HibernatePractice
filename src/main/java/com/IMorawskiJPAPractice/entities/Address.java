package com.IMorawskiJPAPractice.entities;

import javax.persistence.*;

@Embeddable
public class Address {
    private String zipCode;
    private String city;
    private String street;

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
