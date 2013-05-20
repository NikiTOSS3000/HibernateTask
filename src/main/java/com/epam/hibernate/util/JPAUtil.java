package com.epam.hibernate.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.log4j.Logger;

public class JPAUtil {
    private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();
    private static final Logger logger = Logger.getLogger("com.epam.hibernate.util");
    
    private static EntityManagerFactory buildEntityManagerFactory() {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("employeeList");
            return emf;
        } catch (Throwable ex) {
            logger.error(ex.getMessage());
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    private JPAUtil(){}
    
    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }
}
