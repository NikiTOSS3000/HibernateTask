package com.epam.hibernate.util;

import com.epam.hibernate.dao.EmployeeDAO;
import com.epam.hibernate.resources.Constants;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

public class JPAUtil {

    public static EmployeeDAO getEmployeeDao() {
        WebApplicationContext listener = ContextLoaderListener.getCurrentWebApplicationContext();
        EmployeeDAO employeeDAO = (EmployeeDAO) listener.getBean(Constants.EMPLOYEE_DAO);
        return employeeDAO;
    }
}
