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
                    <c:choose>
                        <c:when test="${question.correctAnswers.size() == studentAnswersMap.get(question).size()
                            && question.correctAnswers.containsAll(studentAnswersMap.get(question))}">
                            <div class="panel panel-success">
                                <div class="panel-heading">
                                    <div class="panel-title">
                                        Question ${question.questionId}/${testSize}
                                        <span class="glyphicon glyphicon-ok pull-right"></span>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <div class="panel-title">${question.questionText}</div>
                                    <br>
                                    <c:forEach var="answer" items="${question.answers}">
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-1">
                                                    <p class="text-center">
                                                        <c:choose>
                                                            <c:when test="${question.correctAnswers.contains(answer.answerId)}">
                                                                <span class="glyphicon glyphicon-ok-circle text-success"></span>
                                                            </c:when>
                                                        </c:choose>
                                                    </p>
                                                </div>
                                                <div class="col-sm-11">
                                                        ${answer.answerId}. ${answer.answerText}
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:when>

                        <c:otherwise>
                            <div class="panel panel-danger">
                                <div class="panel-heading">
                                    <div class="panel-title">
                                        Question ${question.questionId}/${testSize}
                                        <span class="glyphicon glyphicon-remove pull-right"></span>
                                    </div>
                                </div>
                                <div class="panel-body">
                                    <div class="panel-title">${question.questionText}</div>
                                    <br>
                                    <c:forEach var="answer" items="${question.answers}">
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-1">
                                                    <p class="text-center">
                                                        <c:choose>
                                                            <c:when test="${studentAnswersMap.get(question).contains(answer.answerId)
                                                            && question.correctAnswers.contains(answer.answerId) }">
                                                                <span class="glyphicon glyphicon-ok-circle text-success"></span>
                                                            </c:when>
                                                            <c:when test="${question.correctAnswers.contains(answer.answerId)
                                                            && !studentAnswersMap.get(question).contains(answer.answerId)}">
                                                                <span class="glyphicon glyphicon-ok text-success"></span>
                                                            </c:when>
                                                            <c:when test="${!question.correctAnswers.contains(answer.answerId)
                                                            && studentAnswersMap.get(question).contains(answer.answerId)}">
                                                                <span class="glyphicon glyphicon-remove-circle text-danger"></span>
                                                            </c:when>
                                                        </c:choose>
                                                    </p>
                                                </div>
                                                <div class="col-sm-11">
                                                        ${answer.answerId}. ${answer.answerText}
                                                </div>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
