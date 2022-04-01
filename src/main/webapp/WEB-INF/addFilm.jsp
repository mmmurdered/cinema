<%--
  Created by IntelliJ IDEA.
  User: murdered
  Date: 18.02.2022
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <label class="col-lg-3 control-label">Title:</label>
                    <div class="col-lg-8">
                        <input name="title"  class="form-control" type="text" value="" minlength="1" maxlength="100">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Description:</label>
                    <div class="col-lg-8">
                        <input name="description"  class="form-control" type="text" value="" minlength="1" maxlength="2000">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Genre:</label>
                    <div class="col-lg-8">
                        <input name="genre"  class="form-control" type="text" value="" minlength="1" maxlength="45">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">Duration:</label>
                    <div class="col-lg-8">
                        <input name="duration"  class="form-control" type="number" value="" min="1">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-3 control-label">IMDB:</label>
                    <div class="col-lg-8">
                        <input name="imdb"  class="form-control" type="number" value="" min="0" max="10" step="0.1">
                    </div>
                </div>
                <div>
                    <button class="w-100 btn btn-dark btn-lg btn-primary" type="submit">Add</button>
                    <a class="nav-item nav-link" href="${pageContext.request.contextPath}/cabinet">Undo</a>
                </div>
            </form>
        </div>
    </div>
</form>

</body>
</html>
