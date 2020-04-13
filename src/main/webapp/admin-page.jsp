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
    <title>Admin Page</title>
</head>

<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: dodgerblue">
        <div>
            <h1>My first CRUD Application</h1>
            <h2><a href="${pageContext.request.contextPath}/admin">
                <button type="button" class="btn btn-secondary">User's list</button>
            </a></h2>
        </div>
    </nav>
</header>

<br>

<div class="row">

    <div class="container">
        <h1 class="text-center">User's list</h1>
        <hr>
        <div class="container text-left">
            <a href="${pageContext.request.contextPath}/create" class="btn btn-primary">Add New User</a>
        </div>
        <br>

        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Age</th>
                <th>Email</th>
                <th>Location</th>
                <th>Password</th>
                <th>Role</th>
                <th>Options</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="user" items="${userList}">
                <tr>
                    <td><c:out value="${user.id}"/></td>
                    <td><c:out value="${user.name}"/></td>
                    <td><c:out value="${user.age}"/></td>
                    <td><c:out value="${user.email}"/></td>
                    <td><c:out value="${user.location}"/></td>
                    <td><c:out value="${user.password}"/></td>
                    <td><c:out value="${user.role}"/></td>
                    <td><a href="update?id=<c:out value='${user.id}' />">
                        <button type="button" class="btn btn-primary">Edit</button>
                    </a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a href="delete?id=<c:out value='${user.id}' />">
                            <button type="button" class="btn btn-primary">Delete</button>
                        </a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>

        <a href="${pageContext.request.contextPath}/user">
            <button type="button" class="btn btn-primary">User Page</button>
        </a>

        <a href="${pageContext.request.contextPath}/logout">
            <button type="button" class="btn btn-primary">LogOut</button>
        </a>
    </div>
</div>
</body>
</html>