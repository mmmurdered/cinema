<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.murdered.cinema.model.Film" %>
<%@ page import="java.util.List" %>
<%@ page import="com.murdered.cinema.model.user.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<%--
  Created by IntelliJ IDEA.
  User: murdered
  Date: 10.02.2022
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/schedule"><fmt:message key="label.schedule"/></a>
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/films"><fmt:message key="label.films"/></a>
            </div>
            <div>
                <% User user = (User) request.getSession().getAttribute("user");
                    if(user != null)
                        response.getWriter().println("Hello, " + user.getLogin() + "!");
                %> <%--TODO maybe--%>
            </div>
        </div>
    </nav>
</div>

<div class="col-auto">
    <label><fmt:message key="label.yourTickets"/>: </label>
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th><fmt:message key="label.date"/> </th>
            <th><fmt:message key="label.films"/></th>
        </tr>
        </thead>
        <c:forEach items="${ticketDTOList}" var="ticket">
            <tr>
                <td><c:out value="${ticket.getSession().getTime()}"/></td>
                <td><c:out value="${ticket.getFilm().getTitle()}"/></td>
            </tr>
        </c:forEach>

    </table>
</div>

</body>
</html>
