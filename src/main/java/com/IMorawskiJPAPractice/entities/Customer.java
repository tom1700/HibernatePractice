package com.IMorawskiJPAPractice.entities;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer extends Company {
    @OneToMany
    private List<Product> products = new ArrayList<>();
    @OneToMany
    private List<Sale> transactions = new ArrayList<>();
    private int discount;

    public Customer() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Sale> getSales() {
        return transactions;
    }

    public int getDiscount() {
        return discount;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setSales(List<Sale> transactions) {
        this.transactions = transactions;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
