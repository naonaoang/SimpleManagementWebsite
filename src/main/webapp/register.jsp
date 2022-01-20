<%--
  Created by IntelliJ IDEA.
  User: gavin
  Date: 1/1/2022
  Time: 6:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="nav.jsp" %>
    <title>Register</title>
</head>
<body>
<div class="container">
    <form:form method="POST" action="/create" modelAttribute="account">
        <div class="form-group">
            <form:label path="username" for="usernameInput">Username</form:label>
            <form:input type="text" path="username" class="form-control" id="usernameInput"/>
            <form:errors path="username" style="color:red;"/>
        </div>
        <div class="form-group">
            <form:label path="pwd" for="pwdInput">Password</form:label>
            <form:input type="text" path="pwd" class="form-control" id="pwdInput"/>
            <form:errors path="pwd" style="color:red;"/>
        </div>
        <button type="submit" class="btn btn-primary">Add</button>
    </form:form>
</div>
</body>
</html>
