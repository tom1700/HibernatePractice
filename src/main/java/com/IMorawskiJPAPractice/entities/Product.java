package com.IMorawskiJPAPractice.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue()
    private int id;
    @ManyToOne
    private Supplier supplier;
    @ManyToOne
    private Category category;
    @ManyToMany
    private List<ProductSale> productSales = new ArrayList<>();
    private String productName;
    private int unitsOnStock;

    public Product() {
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setUnitsOnStock(int unitsOnStock) {
        this.unitsOnStock = unitsOnStock;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setTransactions(List<ProductSale> productSales) {
        this.productSales = productSales;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getUnitsOnStock() {
        return unitsOnStock;
    }

    public Category getCategory() {
        return category;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public List<ProductSale> getTransactions() {
        return productSales;
    }
}
