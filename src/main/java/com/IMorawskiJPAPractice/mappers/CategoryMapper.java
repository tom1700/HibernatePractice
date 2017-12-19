package com.IMorawskiJPAPractice.mappers;

import com.IMorawskiJPAPractice.entities.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CategoryMapper implements Mapper<Category>{
    private static ObjectMapper mapper = new ObjectMapper();


    @Override
    public Category mapJsonToObject(String categoryJson, Session session) throws IOException {
        HashMap<String, String> categoryForm = mapper.readValue(categoryJson, HashMap.class);

        Category category = new Category();
        category.setName(categoryForm.get("name"));

        return category;
    }

    @Override
    public String mapObjectToJson(Category category) throws IOException {
        try {
            Map<String,String> payload = new HashMap<>();
            payload.put("id", String.valueOf(category.getId()));
            payload.put("name", category.getName());

            return mapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
}
