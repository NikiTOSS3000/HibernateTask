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
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Country country = new Country("Belarus");
        City city = new City("Minsk", country);
        Address address = new Address("lala", city);
        session.save(address);
        session.flush();
        session.clear();
        transaction.commit();
        /*String page = CommandFactory.getInstance().getCommand(req).execute(req, resp);
        if (page != null) {
            req.getRequestDispatcher(page).forward(req, resp);
        }*/
    }
}
