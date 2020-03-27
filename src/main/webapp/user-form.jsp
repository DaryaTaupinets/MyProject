<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>

<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${user != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${user == null}">
                <form action="create" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${user != null}">
                                Edit User
                            </c:if>
                            <c:if test="${user == null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${user != null}">
                        <input type="hidden" name="id" value="<c:out value='${user.id}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>User Name</label> <input type="text"
                                                        value="<c:out value='${user.name}' />"
                                                        class="form-control"
                                                        name="name" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User Age</label> <input type="number"
                                                       value="<c:out value='${user.age}' />"
                                                       class="form-control"
                                                       name="age" required="required">
                    </fieldset>


                    <fieldset class="form-group">
                        <label>User Email</label> <input type="email"
                                                         value="<c:out value='${user.email}' />"
                                                         class="form-control"
                                                         name="email">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>User Location</label> <input type="text"
                                                            value="<c:out value='${user.location}' />"
                                                            class="form-control"
                                                            name="location">
                    </fieldset>

                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
        </div>
    </div>
</div>

</body>
</html>