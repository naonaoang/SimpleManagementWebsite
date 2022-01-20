<%--
  Created by IntelliJ IDEA.
  User: gavin
  Date: 1/1/2022
  Time: 5:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <title>Home Page</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href=" ">Navigation</a >
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="navbar-nav">
        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/admin/mainpage?adminid=${adminid}">Admin</a>
    </div>
    <div class="navbar-nav">
        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/quiz/profile?uid=${adminid}">Profile</a>
    </div>
    <div class="navbar-nav">
        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/admin/user?adminid=${adminid}&index=1&sort=id">User</a>
    </div>
    <div class="navbar-nav">
        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/admin/quiz?adminid=${adminid}&sort=id">Quiz</a>
    </div>
    <div class="navbar-nav">
        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/admin/result?adminid=${adminid}&index=1&sort=sid">Results</a>
    </div>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
    </div>
    <div class="navbar-nav">
        <a class="nav-item nav-link" href="${pageContext.request.contextPath}/logout" style="color: brown">Sign out</a>
    </div>

</nav>
</body>
</html>