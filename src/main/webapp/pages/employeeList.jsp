<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="/WEB-INF/pagination.tld" prefix="pagination" %>
<script src="js/validation.js" type="text/javascript"></script>

<html>
    <head>
        <title>HibernateTask</title>
    </head>
    <body>
        <script>var employeeCount = ${employeeCount};</script>
        <br>
        <div style="float: left; min-height: 200px;">
            <pagination:ChangePageTag controller="controller" command="list" page="${appropriatePage}" 
                                      perpage="${itemsPerPage}" itemsCount="${employeeCount}"/>
            <table border="1">
                <tr>
                    <th>Number</th>
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
                <c:forEach var="employee" items="${employees}" varStatus="i">
                    <tr>
                        <td>${(appropriatePage - 1) * itemsPerPage + i.index + 1}</td>
                        <td>${employee.firstname} </td>
                        <td>${employee.lastname} </td>
                        <td>${employee.address.address} </td>
                        <c:if test="${fn:length(employee.workplaceList) == 0}">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </c:if>
                        <c:forEach var="workplace" items="${employee.workplaceList}" varStatus="i">
                            <c:if test="${i.index!=0}">
                            <tr>
                                <td></td>
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
            <br>
            <pagination:ChangePageTag controller="controller" command="list" page="${appropriatePage}" 
                                      perpage="${itemsPerPage}" itemsCount="${employeeCount}"/>
        </div>
    </body>
</html>
