<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<%--
  Created by IntelliJ IDEA.
  User: murdered
  Date: 18.02.2022
  Time: 19:24
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

<form action="<%--${pageContext.request.contextPath}/add--%>" method="post">
    <div class="row">
        <!-- edit form column -->
        <div class="col-md-9 personal-info">
            <form class="form-horizontal" role="form">
                <div class="form-group">
                    <label class="col-lg-3 control-label">Film ID:</label>
                    <div class="col-lg-8">
                        <input name="film_id"  class="form-control" type="number" value="" min="1" minlength="1">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><fmt:message key="label.price"/>:</label>
                    <div class="col-lg-8">
                        <input name="price"  class="form-control" type="number" value="" min="1" minlength="1">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label"><fmt:message key="label.availablePlaces"/>:</label>
                    <div class="col-lg-8">
                        <input name="places"  class="form-control" type="number" value="" min="1" minlength="1">
                    </div>
                </div>
                <div>
                    <label class="col-lg-3 control-label"><fmt:message key="label.time"/>:</label>
                    <input type="datetime-local" name="datetime">
                </div>

                <div>
                    <button class="w-100 btn btn-dark btn-lg btn-primary" type="submit"><fmt:message key="label.add"/></button>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/cabinet">Undo</a>
                </div>
            </form>
        </div>
    </div>
</form>

</body>
</html>
