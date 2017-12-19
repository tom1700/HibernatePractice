package com.IMorawskiJPAPractice.mappers;

import com.IMorawskiJPAPractice.entities.Address;
import com.IMorawskiJPAPractice.entities.Customer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CustomerMapper implements Mapper<Customer> {
    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public Customer mapJsonToObject(String json, Session session) throws IOException {
        return null;
    }

    @Override
    public String mapObjectToJson(Customer customer) throws IOException {
        Map<String,String> payload = new HashMap<>();
        payload.put("id", String.valueOf(customer.getId()));
        payload.put("city", customer.getAddress().getCity());
        payload.put("zipCode",customer.getAddress().getZipCode());
        payload.put("street",customer.getAddress().getStreet());
        payload.put("discount",String.valueOf(customer.getDiscount()));
        payload.put("companyName",customer.getCompanyName());

        return mapper.writeValueAsString(payload);
    }

    public Customer mapMapToObject(HashMap<String, String> map) {
        Address address = new Address();
        address.setCity(map.get("city"));
        address.setStreet(map.get("zipCode"));
        address.setZipCode(map.get("street"));

        Customer customer = new Customer();
        customer.setCompanyName(map.get("companyName"));
        customer.setAddress(address);

        return customer;
    }
}
