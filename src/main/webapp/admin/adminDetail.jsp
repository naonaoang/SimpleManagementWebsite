<%--
  Created by IntelliJ IDEA.
  User: gavin
  Date: 1/8/2022
  Time: 5:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <%@ include file="adminNav.jsp" %>
    <title>Title</title>
    <style>
        body{text-align: center}
        table { border-collapse: separate; border-spacing: 10px; } /* cellspacing="5" */
    </style>
</head>
<body>
<table action = "/admin/viewdetail?adminid=${adminid}&uid=${uid}&sid=${sid}" method = "get" class="table">
    <thead>
    <tr>
        <th scope="col">Question ID</th>
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
            <td><c:out value="${history.question.qid}"></c:out></td>
            <td><c:out value="${history.question.description}"></c:out></td>
            <td><c:out value="${history.choices[0].description}"></c:out> </td>
            <td><c:out value="${history.choices[1].description}"></c:out> </td>
            <td><c:out value="${history.answer.description}"></c:out> </td>
            <td><c:out value="${history.pick.description}"></c:out></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<button type="button" onclick="javascript:window.location='/admin/view?adminid=${adminid}&uid=${uid}'">back</button>
</body>
</html>