package com.epam.hibernate.dao;

import com.epam.hibernate.model.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeDAOJdbcCImpl extends AbstractDAO implements EmployeeDAO {

    @Autowired
    protected EmployeeDAOJdbcCImpl(DataSource s) {
        super(s);
    }

    @Override
    public List<Employee> getList(int firstResult, int maxResult) {
        
        return null;
    }
}
