<%--
  Created by IntelliJ IDEA.
  User: gavin
  Date: 1/2/2022
  Time: 2:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <%@ include file="nav_after.jsp" %>
    <title>Title</title>
    <style>
        body{text-align: center}
        table { border-collapse: separate; border-spacing: 10px; } /* cellspacing="5" */
    </style>
</head>
<body>
<table action = "/quiz/detail?uid=${uid}&sid=${sid}" method = "get" class="table">
    <thead>
    <tr>
        <th scope="col">Question</th>
        <th scope="col">Choice1</th>
        <th scope="col">Choice2</th>
        <th scope="col">Right Choice</th>
        <th scope="col">Your Choice</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items ="${histories}" var = "history">
        <tr>
            <td><c:out value="${history.question.description}"></c:out></td>
            <td><c:out value="${history.choices[0].description}"></c:out> </td>
            <td><c:out value="${history.choices[1].description}"></c:out> </td>
            <td><c:out value="${history.answer.description}"></c:out> </td>
            <td><c:out value="${history.pick.description}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<button type="button" onclick="javascript:window.location='/quiz/profile?uid=${uid}'">back</button>
</body>
</html>