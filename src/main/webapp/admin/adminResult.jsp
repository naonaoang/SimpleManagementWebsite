<%--
  Created by IntelliJ IDEA.
  User: gavin
  Date: 1/9/2022
  Time: 6:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="adminNav.jsp" %>
    <title>Title</title>
    <style>
        /* Dropdown Button */
        .dropdown-button {
            background-color: #0000b3;
            color: white;
            padding: 16px;
            font-size: 16px;
            border: none;
        }
        .dropdown {
            position: relative;
            display: inline-block;
        }
        /* Dropdown Content (Hidden by Default) */
        .dropdown-list {
            display: none;
            position: absolute;
            background-color: #f1f1f1;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
            z-index: 1;
        }
        /* Links inside the dropdown */
        .dropdown-list a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            font-family: verdana;
        }
        /* Change color of dropdown links on hover */
        .dropdown-list a:hover {
            background-color: #ddd;
        }
        /* Show the dropdown list on hover */
        .dropdown:hover .dropdown-list {
            display: block;
        }
        /* Change the background color of the dropdown button when the dropdown list is shown */
        .dropdown:hover .dropdown-button {
            background-color: #6666ff;
        }
    </style>
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th scope="col"><a href="${pageContext.request.contextPath}/admin/result?adminid=${adminid}&index=${index}&sort=sid&filter=${filter}" style="text-decoration: none;">Submission ID</a></th>
        <th scope="col"><a href="${pageContext.request.contextPath}/admin/result?adminid=${adminid}&index=${index}&sort=uid&filter=${filter}" style="text-decoration: none;">User ID</a></th>
        <th scope="col"><a href="${pageContext.request.contextPath}/admin/result?adminid=${adminid}&index=${index}&sort=tid&filter=${filter}" style="text-decoration: none;">Quiz Type</a></th>
        <th scope="col">Pass</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items ="${submissions}" var = "s">
        <tr>
            <td><c:out value="${s.SID}"></c:out></td>
            <td><a href="${pageContext.request.contextPath}/admin/view?adminid=${adminid}&uid=${s.userID}" style="text-decoration: none;"><c:out value="${s.userID}"></c:out></a></td>
            <td><a href="${pageContext.request.contextPath}/admin/checkquiz?adminid=${adminid}&tid=${s.typeID}" style="text-decoration: none;"><c:out value="${s.typeID}"></c:out></a></td>
            <td><c:out value="${s.pass}"></c:out></td>
            <td><a href="${pageContext.request.contextPath}/admin/viewdetail?adminid=${adminid}&uid=${s.userID}&sid=${s.SID}">check</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<form action="/admin/result?adminid=${adminid}&index=${index}&sort=${sort}" method="post" >
    <c:if test = "${index != 1}">
        <button type="submit" class="btn btn-primary" id="previous" name = "action" value = "previous">Previous</button>
    </c:if>
    <c:if test = "${index != max}">
        <button type="submit" class="btn btn-primary" id="next" name = "action" value = "next">Next</button>
    </c:if>
</form>
<br>
<div class="dropdown">
    <button class="dropdown-button">Filter</button>
    <div class="dropdown-list">
        <a href="${pageContext.request.contextPath}/admin/result?adminid=${adminid}&index=${index}&sort=${sort}">None</a>
        <a href="${pageContext.request.contextPath}/admin/result?adminid=${adminid}&index=${index}&sort=${sort}&filter=1">1</a>
        <a href="${pageContext.request.contextPath}/admin/result?adminid=${adminid}&index=${index}&sort=${sort}&filter=2">2</a>
        <a href="${pageContext.request.contextPath}/admin/result?adminid=${adminid}&index=${index}&sort=${sort}&filter=3">3</a>
    </div>
</div>
</form>
<br>
</body>
</html>
