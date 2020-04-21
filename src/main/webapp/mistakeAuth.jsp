<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <title>ERROR</title>
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
<h2> <%=request.getAttribute("name")%>, you haven't admin or user rights! Log in and try again</h2>

<br>
<p>
<h2><a href="${pageContext.request.contextPath}/">
    <button type="button" class="btn btn-primary">Try again</button>
</a></h2>
</p>
</body>
</html>