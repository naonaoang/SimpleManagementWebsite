<%--
  Created by IntelliJ IDEA.
  User: gavin
  Date: 1/2/2022
  Time: 12:28 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page session="false" %>
<html>
<head>
    <%@ include file="nav_after.jsp" %>
    <title>Title</title>
</head>
<body>
<form action="/quiz/quiz?tid=${tid}&uid=${uid}&index=${index}" method="post" >
    <p>${index+1}. ${questions.userAnswers.get(index).question.description}<p><br/>
<%--    <input type="text" name = "input" value="${qa.userAnswers.get(index).answer}">--%>
    <c:forEach items ="${questions.userAnswers.get(index).choices}" var = "choice">
        <input type="radio" name="input" value="${choice.cid}"><c:out value="${choice.description}"></c:out>
    </c:forEach>
    <br><br><br>
    <c:if test = "${index != 0}">
    <button type="submit" class="btn btn-primary" id="previous" name = "action" value = "previous">Previous</button>
    </c:if>
    <c:if test = "${index != questions.userAnswers.size()-1}">
    <button type="submit" class="btn btn-primary" id="next" name = "action" value = "next">Next</button>
    </c:if>
    <c:if test = "${index == questions.userAnswers.size()-1}">
    <button type="submit" class="btn btn-primary" id="subQa" name = "action" value = "finish">Submit Questionnaire</button>
    </c:if>
</form>
</body>
</html>
