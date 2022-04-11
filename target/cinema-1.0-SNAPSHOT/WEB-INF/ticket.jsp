<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="message"/>
<%--
  Created by IntelliJ IDEA.
  User: murdered
  Date: 10.02.2022
  Time: 19:04
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
    <form action="${pageContext.request.contextPath}/buy" method="post">
        <input hidden name="session_id" value="${requestScope.id}">
        <input hidden name="session_film_id" value="${requestScope.film_id}">
        <%--<input hidden name="available_places" value="${requestScope.available_places}">--%>
        <%--<input hidden name="session_id" value="${s.getId()}">
        <input hidden name="session_id" value="${s.getId()}">--%>
        <div class="row">
            <!-- edit form column -->
            <div class="col-md-9 personal-info">
                <form class="form-horizontal" role="form" method="post">
                    <div class="form-group">
                        <label class="col-lg-3 control-label"><fmt:message key="label.numOfTickets"/>:</label>
                        <div class="">
                            <input name="numOfTickets" class="form-control" type="number" value="" min="1" max="${requestScope.available_places}">
                        </div>
                    </div>
                    <div class="padding">
                        <div class="row">
                            <div class="container-fluid d-flex justify-content-center">
                                <div class="">
                                    <div class="card">
                                        <div class="card-header">
                                            <div class="row">
                                                <div class="col-md-6"> <span>CREDIT/DEBIT CARD PAYMENT</span> </div>
                                                <div class="col-md-6 text-right" style="margin-top: -5px;"> <img src="https://img.icons8.com/color/36/000000/visa.png"> <img src="https://img.icons8.com/color/36/000000/mastercard.png"> <img src="https://img.icons8.com/color/36/000000/amex.png"> </div>
                                            </div>
                                        </div>
                                        <div class="card-body" style="height: 200px">
                                            <div class="form-group"> <label for="cc-number" class="control-label">CARD NUMBER</label> <input id="cc-number" type="tel" class="input-lg form-control cc-number" autocomplete="cc-number" placeholder="•••• •••• •••• ••••" required> </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <div class="form-group"> <label for="cc-exp" class="control-label">CARD EXPIRY</label> <input id="cc-exp" type="tel" class="input-lg form-control cc-exp" autocomplete="cc-exp" placeholder="•• / ••" required> </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div class="form-group"> <label for="cc-cvc" class="control-label">CARD CVC</label> <input id="cc-cvc" type="tel" class="input-lg form-control cc-cvc" autocomplete="off" placeholder="••••" required> </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div>
                        <button class=" btn btn-dark btn-lg btn-primary" type="submit" value=""><fmt:message key="label.buy"/></button>
                        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/cabinet"><fmt:message key="label.undo"/></a>
                    </div>
                </form>
            </div>
        </div>
    </form>
</div>
</body>
</html>
