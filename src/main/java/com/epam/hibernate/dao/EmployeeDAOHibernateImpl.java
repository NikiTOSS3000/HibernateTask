package com.epam.hibernate.dao;

import com.epam.hibernate.model.Employee;
import com.epam.hibernate.util.MessageManager;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getList(int firstResult, int maxResult) {
        Session session = sessionFactory.getCurrentSession();
        List<Employee> employeeList = session.createCriteria(Employee.class).addOrder(Order.asc("id")).setFirstResult(firstResult).setMaxResults(maxResult).list();
        return employeeList;
    }

    @Override
    public int employeeCount() {
        Session session = sessionFactory.getCurrentSession();
        int answer = ((Long) session.createQuery(MessageManager.getStr("EMPLOYEE_COUNT")).uniqueResult()).intValue();
        return answer;
    }
}
