<%@ page import="com.murdered.cinema.model.Session" %>
<%@ page import="java.util.List" %>
<%@ page import="com.murdered.cinema.model.user.User" %>
<%@ page import="com.murdered.cinema.service.session.SessionService" %>
<%@ page import="com.murdered.cinema.dao.session.SessionDao" %>
<%@ page import="com.murdered.cinema.dao.session.SessionDaoImpl" %>
<%@ page import="com.murdered.cinema.dto.SessionDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<%--
  Created by IntelliJ IDEA.
  User: murdered
  Date: 07.02.2022
  Time: 20:24
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
                <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/schedule"><fmt:message key="label.schedule"/></a>
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/films"><fmt:message key="label.films"/></a>
            </div>
        </div>
            <a href="${pageContext.request.contextPath}/register" class="btn btn-dark mr-2" role="button" aria-pressed="true"><fmt:message key="label.register"/></a>
            <a href="${pageContext.request.contextPath}/login" class="btn btn-dark" role="button" aria-pressed="true"><fmt:message key="label.login"/></a>
    </nav>
</div>
<div class="col-auto">
    <table class="table table-bordered">
        <thead class="thead-dark">
        <tr>
            <th><fmt:message key="label.title"/></th>
            <th><fmt:message key="label.genre"/></th>
            <th><fmt:message key="label.date"/></th>
            <th><fmt:message key="label.seats"/></th>
        </tr>
        </thead>


        <%
            SessionService sessionService = new SessionService(SessionDaoImpl.getInstance());
            List<Session> schedule = sessionService.getAllSessionsAfter(new Timestamp(System.currentTimeMillis()));

            List<SessionDTO> sessionDTOList = new ArrayList<>();

            for(Session session_cinema : schedule){
                SessionDTO sessionDTO = new SessionDTO(session_cinema);
                sessionDTOList.add(sessionDTO);
            }
            sessionDTOList = sessionDTOList.stream().limit(15).collect(Collectors.toList());
            for (SessionDTO session_cinema : sessionDTOList) {
        %>
        <tr>
            <td><%= session_cinema.getSessionFilm().getTitle()%></td>
            <td><%= session_cinema.getSessionFilm().getGenre()%></td>
            <td><%= session_cinema.getTime()%></td>
            <td><%= session_cinema.getAvailablePlaces()%></td>
        </tr>
        <%
            }
        %>

</table>

</div>


</body>
</html>