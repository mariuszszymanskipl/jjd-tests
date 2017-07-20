<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <jsp:include page="partials/meta.jsp"/>
</head>
<body>
<div class="container-fluid">
    <br>
    <div class="row">
        <div class="col-sm-offset-2 col-sm-8">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="panel-title">Question ${question.questionId}/${testSize}</div>
                </div>

                <div class="panel-body">
                    <div class="panel-title">${question.questionText}</div>
                    <br>
                    <form class="form-horizontal" action="startTest" method="post">

                        <c:forEach var="answer" items="${question.answers}">
                            <div class="form-group">
                                <div class="checkbox col-sm-offset-1">
                                    <label>
                                        <input type="checkbox" value="${answer.answerId}"
                                               name="correct_${answer.answerId}"
                                               title="mark correct answer">
                                            ${answer.answerId}. ${answer.answerText}</label>
                                </div>
                            </div>
                        </c:forEach>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-9">
                                <input class="btn btn-default pull-right" type="submit" value="${buttonName}">
                            </div>
                        </div>
                    </form>

                </div>
            </div>

        </div>
    </div>
</div>

</body>
</html>
