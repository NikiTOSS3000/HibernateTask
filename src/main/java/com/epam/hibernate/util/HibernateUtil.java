package com.epam.hibernate.util;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();
    private static final Logger logger = Logger.getLogger("com.epam.hibernate.util");
    
    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        return configuration.configure().buildSessionFactory();
    }
    
    private HibernateUtil(){}
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
