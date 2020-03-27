<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>

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
    </div>
</div>
</body>
</html>