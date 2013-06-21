<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="/WEB-INF/pagination.tld" prefix="pagination" %>
<script src="js/validation.js" type="text/javascript"></script>

<html>
    <head>
        <title>HibernateTask</title>
    </head>
    <body>
        <pagination:ChangePageTag page="${appropriatePage}" perpage="${employeePerPage}" maxpage="${maxPage}"/>
        <div style="float: left; min-height: 500px;">
            <table>
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
        <pagination:ChangePageTag page="${appropriatePage}" perpage="${employeePerPage}" maxpage="${maxPage}"/>
        </div>
        <form action="controller" onsubmit="return validate()" style="position: relative; text-align: center;">
            <script>var employeeCount = ${employeeCount};</script>
            Items per page <br>
            <input type="text" name="employeePerPage" value="${employeePerPage}" id="employeePerPage"/><br>
            Go to appropriate page <br>
            <input type="text" name="appropriatePage" value="${appropriatePage}" id="appropriatePage"/><br>
            <span id="pageError" style="color: red;"></span><br>
            <input type="submit" value="go!"/>
            <input type="hidden" name="command" value="list"/>
        </form>
    </body>
</html>
