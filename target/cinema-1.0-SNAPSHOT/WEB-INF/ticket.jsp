<%--
  Created by IntelliJ IDEA.
  User: murdered
  Date: 10.02.2022
  Time: 19:04
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
                <a class="nav-item nav-link" href="${pageContext.request.contextPath}/about">About us</a>
            </div>
        </div><a href="${pageContext.request.contextPath}/cabinet" class="btn btn-dark  mr-2" role="button" aria-pressed="true">Cabinet</a>
        <a href="${pageContext.request.contextPath}/register" class="btn btn-dark mr-2" role="button" aria-pressed="true">Register</a>
        <a href="${pageContext.request.contextPath}/login" class="btn btn-dark btn-lg" role="button" aria-pressed="true">Login</a>
    </nav>
</div>

<div>
    <form action="${pageContext.request.contextPath}ticket.jsp" method="post">
        <%for (int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){ %>

        <div class="form-check">
            <div>
            <input class="form-check-input " type="checkbox" value="" id="flexCheckDefault">
            </div>
        </div>
        <%    }
        } %>
    </form>
</div>

</body>
</html>
