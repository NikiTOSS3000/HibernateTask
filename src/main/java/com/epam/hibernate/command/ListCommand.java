package com.epam.hibernate.command;

import com.epam.hibernate.dao.EmployeeDAO;
import com.epam.hibernate.dao.EmployeeDAOHibernateImpl;
import com.epam.hibernate.model.*;
import com.epam.hibernate.util.DBGenerator;
import com.epam.hibernate.util.HibernateUtil;
import com.epam.hibernate.util.JPAUtil;
import com.epam.hibernate.util.MessageManager;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListCommand implements ICommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employees = new LinkedList<Employee>();
        EmployeeDAOHibernateImpl employeeDAO = new EmployeeDAOHibernateImpl();
        employees = employeeDAO.getList(0,100);
//        EmployeeDAO dao = JPAUtil.getEmployeeDao();
        request.setAttribute("employees", employees);
//        DBGenerator.generateDB(1);
//        HibernateUtil.getSessionFactory();
        return MessageManager.getStr("LIST_PAGE_PATH");
    }
    
}
