package com.epam.hibernate.dao;

import com.epam.hibernate.model.Employee;
import com.epam.hibernate.util.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

public class EmployeeDAOHibernateImpl implements EmployeeDAO {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Employee> getList() {
        int size = 1000;
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Employee> employeeList = session.createCriteria(Employee.class).addOrder(Order.asc("id")).list();
        transaction.commit();
        return employeeList;
    }
    
}
