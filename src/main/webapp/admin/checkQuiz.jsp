<%--
  Created by IntelliJ IDEA.
  User: gavin
  Date: 1/9/2022
  Time: 4:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<table action = "/admin/checkquiz?admin=${adminid}&tid=${tid}&index=${index}" method = "get" class="table">
    <thead>
    <tr>
        <th scope="col">Question</th>
        <th scope="col">Choice1</th>
        <th scope="col">Choice2</th>
        <th scope="col">Right Choice</th>
        <th scope="col"><a href="${pageContext.request.contextPath}/admin/newproblem?adminid=${adminid}&tid=${tid}">Add New Problem</a></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items ="${problems}" var = "problem">
        <tr>
            <td><c:out value="${problem.question.description}"></c:out></td>
            <td><c:out value="${problem.choiceList[0].description}"></c:out> </td>
            <td><c:out value="${problem.choiceList[1].description}"></c:out> </td>
            <td><c:out value="${problem.rightChoice.description}"></c:out> </td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/modifyproblem?adminid=${adminid}&tid=${tid}&qid=${problem.question.qid}&cid0=${problem.choiceList[0].cid}&cid1=${problem.choiceList[1].cid}">
                Modify
                </a>
            </td>
            <td><form action="/admin/checkquiz?adminid=${adminid}&tid=${tid}&index=${index}&qid=${problem.question.qid}" method="post">
                <c:if test="${problem.question.status == 'active'}"><button type="submit" id="disable" name = "action" value = "disable">Disable</button></c:if>
                <c:if test="${problem.question.status == 'disabled'}"><button type="submit" id="active" name = "action" value = "active">Active</button></c:if>
            </form></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<form action="/admin/checkquiz?adminid=${adminid}&tid=${tid}&index=${index}" method="post" >
    <c:if test = "${index != 1}">
        <button type="submit" class="btn btn-primary" id="previous" name = "action" value = "previous">Previous</button>
    </c:if>
    <c:if test = "${index != max}">
        <button type="submit" class="btn btn-primary" id="next" name = "action" value = "next">Next</button>
    </c:if>
</form>
<br>
<button type="button" onclick="javascript:window.location='/admin/quiz?adminid=${adminid}&sort=id'">back</button>

</body>
</html>
