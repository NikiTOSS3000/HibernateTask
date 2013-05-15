package com.epam.hibernate.command;

import com.epam.hibernate.dao.EmployeeDAOHibernateImpl;
import com.epam.hibernate.model.*;
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
//        employees = employeeDAO.getList();
//        request.setAttribute("employees", employees);
        Country country = new Country("Belarus", new LinkedList<City>());
        City city = new City("Minsk", null);
        city.setCountry(country);
        country.getCityList().add(city);
  
        Address adr1 = new Address();
        adr1.setAddress("lala");
        adr1.setCity(city);
        
        Address adr2 = new Address();
        adr2.setAddress("papa");
        adr2.setCity(city);
  
        city.getAddressList().add(adr1);
        city.getAddressList().add(adr2);
        
        Company company = new Company("epam", new LinkedList<Office>());
        Office office = new Office(adr1, new LinkedList<Position>(), company);
        office.setEmployeesCount(10);
        Position position = new Position("programmer", office, null);
        Employee employee = new Employee("Nikita", "Laptsevich", adr2, new LinkedList<Position>());
        employee.getPositionList().add(position);
        position.setOffice(office);
        position.setEmployee(employee);
        office.getPositionList().add(position);
        company.getOfficeList().add(office);
        employees.add(employee);
        request.setAttribute("employees", employees);
        return MessageManager.getStr("LIST_PAGE_PATH");
    }
    
}
