<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 12/28/2021
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <%@ include file="nav.jsp" %>
    <title>Login</title>
</head>
<body>
<form action="/login" method="POST">
    <label for="username">Username</label>
    <input id ="username" type="text" name="username"/>
    <br/>
    <label for="password">password</label>
    <input id = "password" type="text" name="pwd"/>
    <button type="submit">submit</button>
</form>
</body>
</html>
