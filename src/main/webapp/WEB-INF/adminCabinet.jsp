<%@ page import="com.murdered.cinema.model.Film" %>
<%@ page import="java.util.List" %>
<%@ page import="com.murdered.cinema.dao.DBManager" %>
<%@ page import="com.murdered.cinema.model.Session" %><%--
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
        </div>
    </nav>
</div>

<div>
    <table class="table table-bordered">
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


        <%
            List<Film> filmList = DBManager.getInstance().getFilms();

            for (Film film : filmList) {
        %>
        <tr>
            <td><%= film.getId()%></td>
            <td><%= film.getTitle()%></td>
            <td><%= film.getDescription()%></td>
            <td><%= film.getGenre()%></td>
            <td><%= film.getDuration()%></td>
            <td><%= film.getImdbRating()%></td>
            <td>Edit | Delete</td>
        </tr>
        <%
            }
        %>

    </table>
</div>

<div>
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <%--<th>Id</th>--%>
            <th>Title</th>
            <th>Genre</th>
            <th>Date and Time</th>
            <th>Free seats</th>
            <th></th>
            <%--<th>Available</th>--%>
        </tr>
        </thead>


        <%
            List<Session> schedule = DBManager.getInstance().getSchedule(); // !!!!!!!!!!!!!!!!!!!

            for (Session session1 : schedule) {
        %>
        <tr>
            <td><%= session1.getId()%></td>
            <td><%= session1.getSessionFilm()%></td>
            <td><%= session1.getTime()%></td>
            <td>num of seats</td>
            <td>Edit | Delete</td>
        </tr>
        <%
            }
        %>

    </table>
</div>

</body>
</html>
