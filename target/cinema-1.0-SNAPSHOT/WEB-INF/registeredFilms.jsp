<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="com.murdered.cinema.model.Film" %>
<%@ page import="com.murdered.cinema.service.film.FilmService" %>
<%@ page import="com.murdered.cinema.dao.film.FilmDaoImpl" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<!doctype html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <%--    <a class="navbar-brand" href="/schedule">CINEMA</a>--%>

    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/schedule"><fmt:message key="label.schedule"/></a>
            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/films"><fmt:message key="label.films"/></a>
        </div>
    </div>
        <div>
            <a href="${pageContext.request.contextPath}/cabinet" class="btn btn-dark  mr-2" role="button" aria-pressed="true"><fmt:message key="label.cabinet"/></a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-dark mr-2" role="button" aria-pressed="true" ><fmt:message key="label.logout"/></a>
        </div>
</nav>

<div class="col-auto">
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th><fmt:message key="label.title"/></th>
            <th><fmt:message key="label.description"/></th>
            <th><fmt:message key="label.genre"/></th>
            <th><fmt:message key="label.duration"/></th>
            <th>IMDB</th>
        </tr>
        </thead>


        <%
            FilmService filmService = new FilmService(FilmDaoImpl.getInstance());
            List<Film> filmList = filmService.getAllFilms();

            for (Film film : filmList) {
        %>
        <tr>
            <td><%= film.getTitle()%></td>
            <td><%= film.getDescription()%></td>
            <td><%= film.getGenre()%></td>
            <td><%= film.getDuration()%></td>
            <td><%= film.getImdbRating()%></td>
        </tr>
        <%
            }
        %>

    </table>
</div>

</body>
</html>