package com.IMorawskiJPAPractice.mappers;

import org.hibernate.Session;

import java.io.IOException;

public interface Mapper <E> {
    E mapJsonToObject(String json, Session session) throws IOException;
    String mapObjectToJson(E object) throws IOException;
}
