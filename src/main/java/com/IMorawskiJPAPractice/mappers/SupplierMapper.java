package com.IMorawskiJPAPractice.mappers;

import com.IMorawskiJPAPractice.entities.Address;
import com.IMorawskiJPAPractice.entities.Supplier;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SupplierMapper implements Mapper<Supplier>{
    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public String mapObjectToJson(Supplier supplier) {
        try {
            Map<String,String> payload = new HashMap<>();
            payload.put("id", String.valueOf(supplier.getId()));
            payload.put("city", supplier.getAddress().getCity());
            payload.put("zipCode",supplier.getAddress().getZipCode());
            payload.put("street",supplier.getAddress().getStreet());
            payload.put("bankAccount",supplier.getBankAccount());
            payload.put("companyName",supplier.getCompanyName());

            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            return "";
        }
    }

    @Override
    public Supplier mapJsonToObject(String supplierJson, Session session) throws IOException {
        HashMap<String, String> supplierForm = mapper.readValue(supplierJson, HashMap.class);

        Address address = new Address();
        address.setCity(supplierForm.get("city"));
        address.setStreet(supplierForm.get("zipCode"));
        address.setZipCode(supplierForm.get("street"));

        Supplier supplier = new Supplier();
        supplier.setBankAccount(supplierForm.get("bankAccount"));
        supplier.setCompanyName(supplierForm.get("companyName"));
        supplier.setAddress(address);

        return supplier;
    }
}
