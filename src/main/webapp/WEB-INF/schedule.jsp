<%@ page import="com.murdered.cinema.model.Session" %>
<%@ page import="java.util.List" %>
<%@ page import="com.murdered.cinema.model.user.User" %>
<%@ page import="com.murdered.cinema.service.session.SessionService" %>
<%@ page import="com.murdered.cinema.dao.session.SessionDaoImpl" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: murdered
  Date: 15.02.2022
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
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
                <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/schedule">Schedule <span class="sr-only">(current)</span></a>
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/films">Films</a>
            </div>
        </div>
            <div>
                <a href="${pageContext.request.contextPath}/cabinet" class="btn btn-dark  mr-2" role="button" aria-pressed="true">Cabinet</a>
                <a href="${pageContext.request.contextPath}/logout" class="btn btn-dark mr-2" role="button" aria-pressed="true">Logout</a>
            </div>
    </nav>
</div>

<div>
    <form method="get">
    <%--<a class="btn btn-dark" href="${pageContext.request.contextPath}/schedule?sort=name">Sort by name</a>
    <a class="btn btn-dark" href="${pageContext.request.contextPath}/schedule?sort=time">Sort by time</a>
    <a class="btn btn-dark" href="${pageContext.request.contextPath}/schedule?sort=available_places">Sort by places</a>--%>
        <div>
            <select name="sort">
                <option value disabled selected>Select type of sort</option>
                <option value="name">Sort by name</option>
                <option value="time" >Sort by time</option>
                <option value="available_places">Sort by places</option>
            </select>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" value="available" id="flexCheckDefault" name="isAvailable">
                <label class="form-check-label" for="flexCheckDefault">
                    Available sessions
                </label>
                <input type="submit" value="Submit">
            </div>
        </div>
    </form>
</div>

<div class="col-auto">
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>Title</th>
            <th>Genre</th>
            <th>Date and Time</th>
            <th>Free seats</th>
            <th></th>
        </tr>
        </thead>

        <c:forEach items="${sessionListCinema}" var="session_cinema">
            <tr>
                <td><c:out value="${session_cinema.getSessionFilm().getTitle()}"/></td>
                <td><c:out value="${session_cinema.getSessionFilm().getGenre()}"/></td>
                <td><c:out value="${session_cinema.getTime()}"/></td>
                <td><c:out value="${session_cinema.getAvailablePlaces()}"/></td>
                <td>
                    <a href="${pageContext.request.contextPath}/buy?session_id=${session_cinema.getId()}&session_film_id=${session_cinema.getSessionFilm().getId()}"
                       class="btn btn-dark btn-sm" role="button" aria-pressed="true">Buy
                    </a>
                </td>
            </tr>
        </c:forEach>
        <tr>
    </table>

</div>
</body>
</html>