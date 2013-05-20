package com.epam.hibernate.command;

import com.epam.hibernate.dao.EmployeeDAOHibernateImpl;
import com.epam.hibernate.model.*;
import com.epam.hibernate.util.DBGenerator;
import com.epam.hibernate.util.JPAUtil;
import com.epam.hibernate.util.MessageManager;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListCommand implements ICommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employees = new LinkedList<Employee>();
        EmployeeDAOHibernateImpl employeeDAO = new EmployeeDAOHibernateImpl();
        employees = employeeDAO.getList(0,100);
//        EntityManagerFactory factory = JPAUtil.getEntityManagerFactory();
//        EntityManager em = factory.createEntityManager();
//        Address address = (Address) em.find(Address.class, 287);
//        System.out.println(address.getAddress());
        request.setAttribute("employees", employees);
        return MessageManager.getStr("LIST_PAGE_PATH");
    }
    
}
