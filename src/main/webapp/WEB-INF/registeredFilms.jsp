<%@ page import="java.util.List" %>
<%@ page import="com.murdered.cinema.model.Film" %>
<%@ page import="com.murdered.cinema.service.film.FilmService" %>
<%@ page import="com.murdered.cinema.dao.film.FilmDaoImpl" %>
<!DOCTYPE html>
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
            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/schedule">Schedule</a>
            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/films">Films<span class="sr-only">(current)</span></a>
        </div>
    </div>
        <div>
            <a href="${pageContext.request.contextPath}/cabinet" class="btn btn-dark  mr-2" role="button" aria-pressed="true">Cabinet</a>
            <a href="${pageContext.request.contextPath}/logout" class="btn btn-dark mr-2" role="button" aria-pressed="true" >Logout</a>
        </div>
</nav>

<div class="col-auto">
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th>Id</th>
            <th>Title</th>
            <th>Description</th>
            <th>Genre</th>
            <th>Duration</th>
            <th>IMDB</th>
        </tr>
        </thead>


        <%
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
        </tr>
        <%
            }
        %>

    </table>
</div>

</body>
</html>