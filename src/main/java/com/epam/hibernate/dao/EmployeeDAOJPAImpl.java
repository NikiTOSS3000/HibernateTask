package com.epam.hibernate.dao;

import com.epam.hibernate.model.Employee;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EmployeeDAOJPAImpl implements EmployeeDAO {

    final static Logger logger = Logger.getLogger("com.epam.hibernate.dao");
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> getList(int firstResult, int maxResult) {
        return null;
    }
}
