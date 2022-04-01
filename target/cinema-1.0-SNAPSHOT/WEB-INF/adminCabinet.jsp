<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.murdered.cinema.model.Film" %>
<%@ page import="java.util.List" %>

<%@ page import="com.murdered.cinema.model.Session" %>
<%@ page import="com.murdered.cinema.model.user.User" %>
<%@ page import="com.murdered.cinema.service.film.FilmService" %>
<%@ page import="com.murdered.cinema.dao.film.FilmDaoImpl" %>
<%@ page import="com.murdered.cinema.service.session.SessionService" %>
<%@ page import="com.murdered.cinema.dao.session.SessionDaoImpl" %><%--
  Created by IntelliJ IDEA.
  User: murdered
  Date: 10.02.2022
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/schedule">Schedule <span class="sr-only">(current)</span></a>
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/films">Films</a>
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
            <a class="btn btn-dark" href="${pageContext.request.contextPath}/editFilm?action=addFilm">Add new film</a>
        </div>

        <thead class="thead-dark">
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Description</th>
            <th>Genre</th>
            <th>Duration</th>
            <th>IMDB</th>
            <th>Action</th>
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
                        Delete</a> <%--todo--%>
                </td>
            </tr>
        </c:forEach>

        <%--<%
            FilmService filmService = new FilmService(FilmDaoImpl.getInstance());
            List<Film> filmList = filmService.getAllFilms();

            for (Film film : filmList) {
        %>
        <tr>
            <td><%= film.getId()%></td>
            <td><%= film.getTitle()%></td>
            <td><%= film.getDescription()%></td>
            <td><%= film.getGenre()%></td>
            <td><%= film.getDuration()%></td>
            <td><%= film.getImdbRating()%></td>
            <td>
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/editFilm?action=deleteFilm&id=<%= film.getId()%>">Delete</a> &lt;%&ndash;todo&ndash;%&gt;
            </td>
        </tr>
        <%
            }
        %>--%>

    </table>

</div>
<div class="col-auto">
    <table class="table table-bordered">
        <div>
            <a class="btn btn-dark" href="${pageContext.request.contextPath}/editSession?action=addSession">Add new session</a>
        </div>
        <thead class="thead-dark">
        <tr>
            <%--<th>Id</th>--%>
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
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/editSession?action=deleteSession&id=${session_cinema.getId()}">
                        Delete</a> <%--todo--%>
                </td>
            </tr>
        </c:forEach>
    </table>

</div>

</body>
</html>
