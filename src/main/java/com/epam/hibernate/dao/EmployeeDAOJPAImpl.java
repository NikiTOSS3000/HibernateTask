package com.epam.hibernate.dao;

import com.epam.hibernate.model.Employee;
import com.epam.hibernate.util.MessageManager;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class EmployeeDAOJPAImpl implements EmployeeDAO {

    final static Logger logger = Logger.getLogger("com.epam.hibernate.dao");
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Employee> getList(int firstResult, int maxResult) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> root = criteriaQuery.from(Employee.class);
        criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
        root.fetch("address").fetch("city").fetch("country");
        criteriaQuery.select(root);
        List<Employee> employees = entityManager.createQuery(criteriaQuery)
                .setFirstResult(firstResult).setMaxResults(maxResult).getResultList();
        return employees;
    }

    @Override
    public int employeeCount() {
        return entityManager.createQuery(MessageManager.getStr("EMPLOYEE_COUNT")).getFirstResult();
    }
}
