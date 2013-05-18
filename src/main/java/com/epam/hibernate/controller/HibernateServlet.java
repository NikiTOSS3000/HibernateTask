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
        String page = CommandFactory.getInstance().getCommand(req).execute(req, resp);
        if (page != null) {
            req.getRequestDispatcher(page).forward(req, resp);
        }
    }
}
