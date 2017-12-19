package com.IMorawskiJPAPractice.mappers;

import com.IMorawskiJPAPractice.entities.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductMapper implements Mapper<Product>{
    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public String mapObjectToJson(Product product) {
        try {
            Map<String,String> payload = new HashMap<>();
            payload.put("id", String.valueOf(product.getId()));
            payload.put("productName", product.getProductName());
            payload.put("unitsOnStock", String.valueOf(product.getUnitsOnStock()));
            if (product.getSupplier() != null) {
                payload.put("supplierId", String.valueOf(product.getSupplier().getId()));
            }
            if (product.getCategory() != null) {
                payload.put("categoryId", String.valueOf(product.getCategory().getId()));
            }

            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    @Override
    public Product mapJsonToObject(String productJson, Session session) throws IOException {
        HashMap<String, String> productForm = mapper.readValue(productJson, HashMap.class);

        Product product = new Product();
        product.setProductName(productForm.get("productName"));
        product.setUnitsOnStock(Integer.parseInt(productForm.get("unitsOnStock")));
        if (productForm.containsKey("supplierId") && !productForm.get("supplierId").equals("")) {
            product.setSupplier(session.get(Supplier.class, Integer.parseInt(productForm.get("supplierId"))));
        }
        if (productForm.containsKey("categoryId") && !productForm.get("categoryId").equals("")) {
            product.setCategory(session.get(Category.class, Integer.parseInt(productForm.get("categoryId"))));
        }

        return product;
    }
}
