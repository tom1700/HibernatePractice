package com.IMorawskiJPAPractice.server;
import static spark.Spark.*;

public class Main {
    static Api api = new Api();

    public static void main(String[] args) {
        staticFiles.externalLocation(System.getProperty("user.dir") + "/src/main/resources/public");
        // Prod: staticFiles.location("/public");

        get("/", (req, res) -> {
            res.redirect("/index.html");
            return null;
        });

        get("/api/v1/suppliers", (req, res) -> {
            return api.getSuppliers();
        });

        get("/api/v1/products", (req, res) -> {
            return api.getProducts();
        });

        get("/api/v1/categories", (req, res) -> {
            return api.getCategories();
        });

        get("/api/v1/transactions", (req, res) -> {
            return api.getTransactions();
        });

        get("/api/v1/customers", (req, res) -> {
            return api.getCustomers();
        });

        post("/api/v1/product", (req, res) -> {
            return api.saveProduct(req);
        });

        post("/api/v1/transaction", (req, res) -> {
            return api.saveTransaction(req);
        });

        post("/api/v1/category", (req, res) -> {
            return api.saveCategory(req);
        });

        post("/api/v1/supplier", (req, res) -> {
            return api.saveSupplier(req);
        });

        post("/api/v1/customer", (req, res) -> {
            return api.saveCustomer(req);
        });

        after((req, res) -> {
            res.type("application/json");
        });
    }
}
