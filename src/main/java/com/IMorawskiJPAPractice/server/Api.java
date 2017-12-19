package com.IMorawskiJPAPractice.server;

import com.IMorawskiJPAPractice.entities.*;
import com.IMorawskiJPAPractice.mappers.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import spark.Request;

import java.io.IOException;
import java.util.*;

public class Api {
    private SessionFactory sessionFactory = new SessionFactoryFactory().getSessionFactory();
    private ObjectMapper objectMapper = new ObjectMapper();
    private SupplierMapper supplierMapper = new SupplierMapper();
    private ProductMapper productMapper = new ProductMapper();
    private CategoryMapper categoryMapper = new CategoryMapper();
    private CustomerMapper customerMapper = new CustomerMapper();
    private SaleMapper saleMapper = new SaleMapper();

    private String save(Mapper mapper, String json) {
        try {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            session.save(mapper.mapJsonToObject(json, session));
            tx.commit();
            session.close();

            return "{ 'status': 'Success' }";
        } catch (IOException e) {
            return "{ 'status': 'Error' }";
        }
    }

    private String getAll(Class c, Mapper mapper) {
        try {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();
            Query qry = session.createQuery("from " + c.getName() + " s");
            List queryResult = qry.list();
            List objectList = new LinkedList();
            for (Object obj : queryResult) {
                objectList.add(mapper.mapObjectToJson(obj));
            }
            String result = objectMapper.writeValueAsString(objectList);
            tx.commit();
            session.close();

            return result;
        } catch (IOException err) {
            return "{ 'status': 'Error' }";
        }
    }

    public String saveTransaction(Request req) {
        try {
            Session session = sessionFactory.openSession();
            Transaction tx = session.beginTransaction();

            HashMap<String, HashMap> mappedReq = objectMapper.readValue(req.body(), HashMap.class);
            HashMap<String, String> customerMap = mappedReq.get("customer");
            HashMap<String, HashMap> cartMap = mappedReq.get("products");

            Customer customer = customerMapper.mapMapToObject(customerMap);
            session.save(customer);
            com.IMorawskiJPAPractice.entities.Sale transaction = new com.IMorawskiJPAPractice.entities.Sale();
            transaction.setCustomer(customer);
            session.save(transaction);
            for (Map.Entry<String, HashMap> entry: cartMap.entrySet()) {
                HashMap<String, String> productMap = (HashMap) entry.getValue().get("product");
                int amount = Integer.parseInt(entry.getValue().get("amount").toString());
                Product product = session.get(Product.class, Integer.parseInt(productMap.get("id")));
                ProductSale productSale = new ProductSale();
                productSale.setAmount(amount);
                productSale.setProduct(product);
                productSale.setSale(transaction);
                session.save(productSale);
            }

            tx.commit();
            session.close();

            return "{ 'status': 'Success' }";
        } catch (IOException e) {
            return "{ 'status': 'Error' }";
        }
    }

    public String getSuppliers() {
        return getAll(Supplier.class, supplierMapper);
    }

    public  String getProducts() {
       return  getAll(Product.class, productMapper);
    }

    public String getCategories() {
        return getAll(Category.class, categoryMapper);
    }

    public String getCustomers() {
        return getAll(Customer.class, customerMapper);
    }

    public String getTransactions() {
        return getAll(Sale.class, saleMapper);
    }

    public String saveSupplier(Request req) {
        return save(supplierMapper, req.body());
    }

    public String saveProduct(Request req) {
        return save(productMapper, req.body());
    }

    public String saveCategory(Request req) {
        return save(categoryMapper, req.body());
    }

    public String saveCustomer(Request req) {
        return "";
    }

}
