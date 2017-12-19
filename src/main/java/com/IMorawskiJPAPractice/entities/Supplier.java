package com.IMorawskiJPAPractice.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Supplier extends Company{
    @OneToMany
    private List<Product> products = new ArrayList<>();
    @OneToMany
    private List<Sale> transactions = new ArrayList<>();
    private String bankAccount;

    public Supplier() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Sale> getSales() {
        return transactions;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setSales(List<Sale> transactions) {
        this.transactions = transactions;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }
}
