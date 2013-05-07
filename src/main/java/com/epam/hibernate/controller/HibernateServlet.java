package com.epam.hibernate.controller;

import com.epam.hibernate.command.CommandFactory;
import com.epam.hibernate.model.Address;
import com.epam.hibernate.model.City;
import com.epam.hibernate.model.Company;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.epam.hibernate.model.Country;
import com.epam.hibernate.model.Employee;
import com.epam.hibernate.model.Office;
import com.epam.hibernate.model.Position;
import com.epam.hibernate.util.HibernateUtil;
import java.util.LinkedList;

public class HibernateServlet extends HttpServlet {
    static final private Logger logger = Logger.getLogger("com.epam.hibernate.controller");

    public HibernateServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
  
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
        Office office = new Office(adr1, new LinkedList<Position>(), 20, company);
        Position position = new Position("programmer", office, null);
        Employee employee = new Employee("Nikita", "Laptsevich", adr2, new LinkedList<Position>());
        employee.getPositionList().add(position);
        position.setEmployee(employee);
        office.getPositionList().add(position);
        company.getOfficeList().add(office);
        
        session.save(country);
        session.save(company);
        session.save(employee);
  
        session.getTransaction().commit();
        session.close();
        /*session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.save(address);
        session.flush();
        session.clear();
        transaction.commit();
        /*session = sessionFactory.openSession();
        session.save(city);
        session.flush();
        session.clear();       */
        //transaction.commit();
        /*String page = CommandFactory.getInstance().getCommand(req).execute(req, resp);
        if (page != null) {
            req.getRequestDispatcher(page).forward(req, resp);
        }*/
    }
}
