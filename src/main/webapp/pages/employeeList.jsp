<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HibernateTask</title>
    </head>
    <body>
        <table >
            <tr>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Address</th>
                <th>Company</th>
                <th>City</th>
                <th>Country</th>
                <th>Address</th>
                <th>Employees</th>
                <th>Position</th>
            </tr>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.firstname} </td>
                    <td>${employee.lastname} </td>
                    <td>${employee.address.address} </td>
                    <c:forEach var="workplace" items="${employee.workplaceList}" varStatus="i">
                        <c:if test="${i.index!=0}">
                            <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:if>
                            <td>${workplace.office.company.name}</td>
                            <td>${workplace.office.address.city.name}</td>
                            <td>${workplace.office.address.city.country.name}</td>
                            <td>${workplace.office.address.address}</td>
                            <td>${workplace.office.employeesCount}</td>
                            <td>${workplace.position.name}</td>
                            </tr>
                    </c:forEach>
            </c:forEach>
        </table>
    </body>
</html>
