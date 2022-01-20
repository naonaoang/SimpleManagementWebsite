<%--
  Created by IntelliJ IDEA.
  User: gavin
  Date: 1/8/2022
  Time: 4:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="adminNav.jsp" %>
    <title>Title</title>
</head>
<body>
<h2>User List</h2>
<table class="table">
    <thead>
    <tr>
        <th scope="col">User ID</th>
        <th scope="col">Username</th>
        <th scope="col">Password</th>
        <th scope="col">User Group</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items ="${accounts}" var = "user">
        <tr>
            <td><c:out value="${user.userID}"></c:out></td>
            <td><c:out value="${user.username}"></c:out></td>
            <td><c:out value="${user.pwd}"></c:out></td>
            <td><c:out value="${user.usergroup}"></c:out></td>
            <td><a href="${pageContext.request.contextPath}/admin/view?adminid=${adminid}&uid=${user.userID}">view taken quiz</a></td>
            <td><form action="/admin/mainpage?adminid=${adminid}&uid=${user.userID}" method="post">
                <c:if test="${user.usergroup == 'user'}"><button type="submit" id="ban" name = "action" value = "ban">Ban</button></c:if>
                <c:if test="${user.usergroup == 'ban'}"><button type="submit" id="unban" name = "action" value = "unban">Unban</button></c:if>
            </form></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<h2>Quiz List</h2>
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
