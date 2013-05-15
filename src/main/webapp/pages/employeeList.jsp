<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                    <c:forEach var="position" items="${employee.positionList}" varStatus="i">
                        <c:if test="${i.index!=0}">
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:if>
                            <td>${position.office.company.name}</td>
                            <td>${position.office.address.city.name}</td>
                            <td>${position.office.address.city.country.name}</td>
                            <td>${position.office.address.address}</td>
                            <td>${position.office.employeesCount}</td>
                            <td>${position.name}</td>
                    </c:forEach>
            </c:forEach>
        </table>
    </body>
</html>
