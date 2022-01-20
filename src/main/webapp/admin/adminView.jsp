<%--
  Created by IntelliJ IDEA.
  User: gavin
  Date: 1/8/2022
  Time: 5:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <%@ include file="adminNav.jsp" %>
    <title>Title</title>
</head>
<body>
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
            <td><a href="${pageContext.request.contextPath}/admin/viewdetail?adminid=${adminid}&uid=${uid}&sid=${submission.SID}">check</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<button type="button" onclick="javascript:window.location='/admin/user?adminid=${adminid}&index=1&sort=id'">back</button>
</body>
</html>
