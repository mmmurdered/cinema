<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="com.murdered.cinema.model.Film" %>
<%@ page import="java.util.List" %>

<%@ page import="com.murdered.cinema.model.Session" %>
<%@ page import="com.murdered.cinema.model.user.User" %>
<%@ page import="com.murdered.cinema.service.film.FilmService" %>
<%@ page import="com.murdered.cinema.dao.film.FilmDaoImpl" %>
<%@ page import="com.murdered.cinema.service.session.SessionService" %>
<%@ page import="com.murdered.cinema.dao.session.SessionDaoImpl" %>
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
        <%--    <a class="navbar-brand" href="/schedule">CINEMA</a>--%>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/schedule"> <fmt:message key="label.schedule"/> </a>
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

<nav aria-label="...">
    <ul class="pagination pagination-lg">
        <c:forEach begin="1" end="${countPage}" var="i">
            <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/cabinet?page=${i}">${i}</a></li>
        </c:forEach>
    </ul>
</nav>

<div class="col-auto">
    <table class="table table-bordered">
        <div>
            <a class="btn btn-dark" href="${pageContext.request.contextPath}/editFilm?action=addFilm"><fmt:message key="label.addFilm"/></a>
        </div>

        <thead class="thead-dark">
        <tr>
            <th>Id</th>
            <th><fmt:message key="label.title"/></th>
            <th><fmt:message key="label.description"/></th>
            <th><fmt:message key="label.genre"/></th>
            <th><fmt:message key="label.duration"/></th>
            <th>IMDB</th>
            <th><fmt:message key="label.action"/> </th>
        </tr>
        </thead>

        <c:forEach items="${filmListCinema}" var="film_cinema">
            <tr>
                <td><c:out value="${film_cinema.getId()}"/></td>
                <td><c:out value="${film_cinema.getTitle()}"/></td>
                <td><c:out value="${film_cinema.getDescription()}"/></td>
                <td><c:out value="${film_cinema.getGenre()}"/></td>
                <td><c:out value="${film_cinema.getDuration()}"/></td>
                <td><c:out value="${film_cinema.getImdbRating()}"/></td>
                <td>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/editSession?action=deleteSession&id=${film_cinema.getId()}">
                        <fmt:message key="label.delete"/></a> <%--todo--%>
                </td>
            </tr>
        </c:forEach>

    </table>

</div>
<div class="col-auto">
    <table class="table table-bordered">
        <div>
            <a class="btn btn-dark" href="${pageContext.request.contextPath}/editSession?action=addSession"><fmt:message key="label.addSession"/></a>
        </div>
        <thead class="thead-dark">
        <tr>
            <%--<th>Id</th>--%>
            <th><fmt:message key="label.title"/></th>
            <th><fmt:message key="label.genre"/></th>
            <th><fmt:message key="label.date"/></th>
            <th><fmt:message key="label.seats"/></th>
            <th><fmt:message key="label.action"/> </th>
        </tr>
        </thead>

        <c:forEach items="${sessionListCinema}" var="session_cinema">
            <tr>
                <td><c:out value="${session_cinema.getSessionFilm().getTitle()}"/></td>
                <td><c:out value="${session_cinema.getSessionFilm().getGenre()}"/></td>
                <td><c:out value="${session_cinema.getTime()}"/></td>
                <td><c:out value="${session_cinema.getAvailablePlaces()}"/></td>
                <td>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/editSession?action=deleteSession&id=${session_cinema.getId()}">
                        <fmt:message key="label.delete"/></a> <%--todo--%>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

</body>
</html>
