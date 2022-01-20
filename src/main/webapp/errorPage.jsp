<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 12/28/2021
  Time: 6:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <%@ include file="nav.jsp" %>
    <title>Title</title>
</head>
<body>
<div class="container">
    <h1>Error Page</h1>
    <h5>${exception}</h5>
    <h5>${url}</h5>
</div>
</body>
</html>
