<%--
  Created by IntelliJ IDEA.
  User: jason
  Date: 12/28/2021
  Time: 6:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<style>
    body{text-align: center}
    table { border-collapse: separate; border-spacing: 10px; } /* cellspacing="5" */
</style>
<head>
    <%@ include file="nav_after.jsp" %>
    <title>Title</title>
</head>
<body>
<table action = "/quiz/profile?id=${uid}" method = "get" class="table">
    <thead>
    <tr>
        <th scope="col">Quiz ID</th>
        <th scope="col">Quiz Description</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items ="${quiztypes}" var = "quiztype">
        <tr>
            <td><c:out value="${quiztype.tid}"></c:out></td>
            <td><c:out value="${quiztype.description}"></c:out></td>
            <td><a href="${pageContext.request.contextPath}/quiz/quiz?uid=${uid}&tid=${quiztype.tid}">start</a></td>
            <td><a href="${pageContext.request.contextPath}/quiz/feedback?uid=${uid}&tid=${quiztype.tid}">feedback</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<br>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Submission ID</th>
        <th scope="col">User ID</th>
        <th scope="col">Type ID</th>
        <th scope="col">Pass</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items ="${submissions}" var = "submission">
        <tr>
            <td><c:out value="${submission.SID}"></c:out></td>
            <td><c:out value="${submission.userID}"></c:out> </td>
            <td><c:out value="${submission.typeID}"></c:out> </td>
            <td><c:out value="${submission.pass}"></c:out> </td>
            <td><a href="${pageContext.request.contextPath}/quiz/detail?uid=${uid}&sid=${submission.SID}">check</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="/logout" method="get">
    <button type="submit">Log out</button>
</form>
</body>
</html>
