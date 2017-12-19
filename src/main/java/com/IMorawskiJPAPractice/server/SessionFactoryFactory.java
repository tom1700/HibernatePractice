package com.IMorawskiJPAPractice.server;

import org.hibernate.cfg.Configuration;

public class SessionFactoryFactory {
    private org.hibernate.SessionFactory sessionFactory = null;

    public org.hibernate.SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();
            sessionFactory = configuration.configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}