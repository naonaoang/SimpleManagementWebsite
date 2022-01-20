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
        <th scope="col"><a href="${pageContext.request.contextPath}/admin/user?adminid=${adminid}&index=${index}&sort=id" style="text-decoration: none;">User ID</a></th>
        <th scope="col"><a href="${pageContext.request.contextPath}/admin/user?adminid=${adminid}&index=${index}&sort=username" style="text-decoration: none;">Username</a></th>
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
            <td><a href="${pageContext.request.contextPath}/admin/view?adminid=${adminid}&uid=${user.userID}">view taken quizs</a></td>
            <td><form action="/admin/user?adminid=${adminid}&index=${index}&sort=${sort}&uid=${user.userID}" method="post">
                <c:if test="${user.usergroup == 'user'}"><button type="submit" id="ban" name = "action" value = "ban">Ban</button></c:if>
                <c:if test="${user.usergroup == 'ban'}"><button type="submit" id="unban" name = "action" value = "unban">Unban</button></c:if>
            </form></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<form action="/admin/user?adminid=${adminid}&index=${index}&sort=${sort}" method="post" >
    <c:if test = "${index != 1}">
        <button type="submit" class="btn btn-primary" id="previous" name = "action" value = "previous">Previous</button>
    </c:if>
    <c:if test = "${index != max}">
        <button type="submit" class="btn btn-primary" id="next" name = "action" value = "next">Next</button>
    </c:if>
</form>
<br>
</body>
</html>
