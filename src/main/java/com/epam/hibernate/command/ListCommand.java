package com.epam.hibernate.command;

import com.epam.hibernate.dao.EmployeeDAO;
import com.epam.hibernate.model.*;
import com.epam.hibernate.util.EmployeeDAOUtil;
import com.epam.hibernate.util.MessageManager;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EmployeeDAO employeeDAO = EmployeeDAOUtil.getEmployeeDao();
        List<Employee> employees = new LinkedList<Employee>();
        int employeePerPage = Integer.parseInt(request.getParameter("employeePerPage"));
        int appropriatePage = Integer.parseInt(request.getParameter("appropriatePage"));
        int employeeCount = employeeDAO.employeeCount();
        int maxPage = (employeeCount - 1) / employeePerPage + 1;
        employees = employeeDAO.getList(employeePerPage * (appropriatePage - 1), employeePerPage);
        request.setAttribute("employees", employees);
        request.setAttribute("employeePerPage", employeePerPage);
        request.setAttribute("appropriatePage", appropriatePage);
        request.setAttribute("maxPage", maxPage);
        request.setAttribute("employeeCount", employeeCount);
        return MessageManager.getStr("LIST_PAGE_PATH");
    }
}
