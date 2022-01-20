<%--
  Created by IntelliJ IDEA.
  User: gavin
  Date: 1/9/2022
  Time: 10:03 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="adminNav.jsp" %>
    <title>Title</title>
</head>
<body>
<div class="container">
    <form:form method="POST" action="/admin/createQuestion?adminid=${adminid}&tid=${tid}" modelAttribute="question">
        <div class="form-group">
            <form:label path="description" for="usernameInput">Question Description</form:label>
            <form:input type="text" path="description" class="form-control" id="usernameInput" placeholder="Question"/>
            <form:errors path="description" style="color:red;"/>
        </div>
        <button type="submit" class="btn btn-primary">Next</button>
    </form:form>
</div>
</body>
</html>