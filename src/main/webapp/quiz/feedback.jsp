<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: gavin
  Date: 1/2/2022
  Time: 3:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <%@ include file="nav_after.jsp" %>
    <title>Feedback</title>
</head>
<body>
<form:form action="/quiz/feedback?uid=${uid}&tid=${tid}" method="POST" modelAttribute = "feedback">
    <form:label path="rating">rating:</form:label>
    <form:radiobutton path="rating" value="5"/>5
    <form:radiobutton path="rating" value="4"/>4
    <form:radiobutton path="rating" value="3"/>3
    <form:radiobutton path="rating" value="2"/>2
    <form:radiobutton path="rating" value="1"/>1
    <br/>
    <form:label path="description" for="desInput">Feedback:</form:label>
    <form:input type="text" path="description" id="desInput" cols="25" rows="5"/><br/>
    <button type="submit">submit</button>
</form:form>
</body>
</html>
