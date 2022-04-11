<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<%--
  Created by IntelliJ IDEA.
  User: murdered
  Date: 08.02.2022
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<!doctype html>
<html>
<head>
    <!-- Bootstrap core CSS -->

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <style>
        body {
            display: flex;
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }
        body {
            height: 100%;
        }

        body {
            display: flex;
            align-items: center;
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }

        .form-signin {
            width: 100%;
            max-width: 330px;
            padding: 15px;
            margin: auto;
        }

        .form-signin .form-floating:focus-within {
            z-index: 2;
        }

        .form-signin input[type="email"] {
            margin-bottom: -1px;
            border-bottom-right-radius: 0;
            border-bottom-left-radius: 0;
        }

        .form-signin input[type="password"] {
            margin-bottom: 10px;
            border-top-left-radius: 0;
            border-top-right-radius: 0;
        }
    </style>

</head>

<body class="text-center">

<main class="form-signin">
    <form action="${pageContext.request.contextPath}/register" method="post">
        <h1 class="h3 mb-3 fw-normal"><fmt:message key="label.pleaseSignIn"/></h1>

        <div class="form-floating">
            <input type="login" name="login" class="form-control"  placeholder="Login" minlength="4" maxlength="16">
            <label><fmt:message key="label.login"/></label>
        </div>

        <div class="form-floating">
            <input type="email" name="email" class="form-control" id="floatingInput" placeholder="name@example.com">
            <label for="floatingInput">Email@</label>
        </div>
        <div class="form-floating">
            <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="<fmt:message key="label.password"/>"
                   minlength="6" maxlength="30">
            <label for="floatingPassword"><fmt:message key="label.password"/></label>
        </div>

        <button class="w-100 btn btn-dark btn-lg btn-primary" type="submit"><fmt:message key="label.register"/></button>

        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/schedule"><fmt:message key="label.backToSchedule"/></a>
    </form>
</main>

</body>
</html>
