package com.epam.hibernate.command;

import com.epam.hibernate.dao.EmployeeDAO;
import com.epam.hibernate.model.*;
import com.epam.hibernate.util.EmployeeDAOUtil;
import com.epam.hibernate.util.MessageManager;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListCommand implements ICommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employees = new LinkedList<Employee>();
//        EmployeeDAOHibernateImpl employeeDAO = new EmployeeDAOHibernateImpl();
//        employees = employeeDAO.getList(0,100);
        EmployeeDAO employeeDAO = EmployeeDAOUtil.getEmployeeDao();
        employees = employeeDAO.getList(0, 5);
        request.setAttribute("employees", employees);
        return MessageManager.getStr("LIST_PAGE_PATH");
    }
    
}
