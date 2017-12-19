package com.IMorawskiJPAPractice.entities;

import javax.persistence.*;

@Entity
public class ProductSale {
    @Id
    @GeneratedValue()
    private int id;
    @ManyToOne
    private Sale transaction;
    @OneToOne
    private Product product;
    private int amount;

    public ProductSale() {

    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public Sale getSale() {
        return transaction;
    }

    public int getAmount() {
        return amount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setSale(Sale transaction) {
        this.transaction = transaction;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
