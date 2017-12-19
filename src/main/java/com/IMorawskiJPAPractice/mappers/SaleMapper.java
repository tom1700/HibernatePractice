package com.IMorawskiJPAPractice.mappers;

import com.IMorawskiJPAPractice.entities.Sale;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SaleMapper implements Mapper<Sale> {
    private static ObjectMapper mapper = new ObjectMapper();

    @Override
    public Sale mapJsonToObject(String json, Session session) throws IOException {
        return null;
    }

    @Override
    public String mapObjectToJson(Sale sale) throws IOException {
        try {
            Map<String,String> payload = new HashMap<>();
            payload.put("transactionNumber", String.valueOf(sale.getTransactionNumber()));
            if (sale.getCustomer() != null) {
                payload.put("customerId", String.valueOf(sale.getCustomer().getId()));
                payload.put("companyName", sale.getCustomer().getCompanyName());
            }

            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
