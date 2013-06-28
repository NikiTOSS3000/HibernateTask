package com.epam.hibernate.command;

import com.epam.hibernate.dao.EmployeeDAO;
import com.epam.hibernate.model.*;
import com.epam.hibernate.resources.Constants;
import com.epam.hibernate.util.EmployeeDAOUtil;
import com.epam.hibernate.util.MessageManager;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class ListCommand implements ICommand {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        EmployeeDAO employeeDAO = EmployeeDAOUtil.getEmployeeDao();
        List<Employee> employees = new LinkedList<Employee>();
        int employeePerPage = Integer.parseInt(request.getParameter(Constants.ITEMS_PER_PAGE));
        int appropriatePage = Integer.parseInt(request.getParameter(Constants.APPROPRIATE_PAGE));
        long employeeCount = employeeDAO.employeeCount();
        employees = employeeDAO.getList(employeePerPage * (appropriatePage - 1), employeePerPage);
        request.setAttribute(Constants.EMPLOYEES, employees);
        request.setAttribute(Constants.ITEMS_PER_PAGE, employeePerPage);
        request.setAttribute(Constants.APPROPRIATE_PAGE, appropriatePage);
        request.setAttribute(Constants.EMPLOYEE_COUNT, employeeCount);
        return MessageManager.getStr("LIST_PAGE_PATH");
    }
}
