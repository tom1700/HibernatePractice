package com.IMorawskiJPAPractice.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sale {
    @Id
    @GeneratedValue()
    private int transactionNumber;
    @ManyToOne
    private Customer customer;
    @ManyToMany
    private List<ProductSale> productSales = new ArrayList<>();
    public Sale() {
    }

    public List<ProductSale> getProducts() {
        return productSales;
    }

    public int getTransactionNumber() {
        return transactionNumber;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setProducts(List<ProductSale> products) {
        this.productSales = products;
    }

    public void setTransactionNumber(int transactionNumber) {
        this.transactionNumber = transactionNumber;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
