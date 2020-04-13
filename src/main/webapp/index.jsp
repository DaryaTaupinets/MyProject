<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <title>Main page</title>
</head>

<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: dodgerblue">
        <div>
            <h1>My first CRUD Application</h1>
        </div>
    </nav>
</header>
<br>
<h2>Hello! Please, enter your login and password</h2>

<form action="${pageContext.request.contextPath}/login" method="post">
    <div class="d-inline-flex p-2 bd-highlight">
        <div class="container-fluid mt3">
            <fieldset class="form-group">
                <label>User Name</label> <input type="text"
                                                value="<c:out value='${user.name}' />"
                                                class="form-control"
                                                placeholder="name"
                                                name="name" required="required">
            </fieldset>

            <fieldset class="form-group">
                <label>Password</label> <input type="password"
                                               value="<c:out value='${user.password}' />"
                                               class="form-control"
                                               placeholder="password"
                                               name="password">
            </fieldset>

            <button type="submit" class="btn btn-primary">Login</button>
        </div>
    </div>
</form>
</body>
</html>