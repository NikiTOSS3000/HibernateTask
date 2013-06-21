package com.epam.hibernate.dao;

import com.epam.hibernate.model.Employee;
import java.util.List;

public interface EmployeeDAO {
    
    public List<Employee> getList(int firstResult, int maxResult);
    public int employeeCount();
}
