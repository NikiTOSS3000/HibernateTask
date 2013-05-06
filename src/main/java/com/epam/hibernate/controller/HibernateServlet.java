package com.epam.hibernate.controller;

import com.epam.hibernate.command.CommandFactory;
import com.epam.hibernate.model.Address;
import com.epam.hibernate.model.City;
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
import com.epam.hibernate.util.HibernateUtil;

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
  
        City city = new City("Minsk", null);
  
        Address adr1 = new Address();
        adr1.setAddress("lala");
        adr1.setCity(city);
        
        Address adr2 = new Address();
        adr2.setAddress("papa");
        adr2.setCity(city);
  
        city.getAddressList().add(adr1);
        city.getAddressList().add(adr2);
        
        session.save(city);
  
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
