<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="partials/meta.jsp"/>
</head>
<body>
<div class="container-fluid">
    <div class="jumbotron text-center">
        <h1>Your score:</h1>
        <h2>${result} / ${testSize}</h2>
        <h2>${percentageResult} %</h2>
        <br>
        <a type="button" class="btn btn-default btn-lg" href="index.jsp">Main page</a>
    </div>
    <div>
        <div class="row">
            <div class="col-sm-offset-2 col-sm-8">
                <c:forEach var="question" items="${studentAnswersMap.keySet()}">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="panel-title">Question ${question.questionId}/${testSize}</div>
                        </div>

                        <div class="panel-body">
                            <div class="panel-title">${question.questionText}</div>
                            <br>
                            <c:forEach var="answer" items="${question.answers}">
                                <div class="form-group">
                                    <div class="col-sm-offset-1">
                                            ${answer.answerId}. ${answer.answerText}
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
