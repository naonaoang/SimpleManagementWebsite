<%--
  Created by IntelliJ IDEA.
  User: gavin
  Date: 1/9/2022
  Time: 5:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="adminNav.jsp" %>
    <title>Title</title>
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Quiz Type ID</th>
        <th scope="col">Description</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items ="${quiztypes}" var = "quiztype">
        <tr>
            <td><c:out value="${quiztype.tid}"></c:out></td>
            <td><c:out value="${quiztype.description}"></c:out></td>
            <td><a href="${pageContext.request.contextPath}/admin/checkquiz?adminid=${adminid}&tid=${quiztype.tid}&index=1">Check</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
