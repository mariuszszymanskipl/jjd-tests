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
                    <div class="panel-title">Question ${questionNumber}/${questionsSize}</div>
                </div>

                <div class="panel-body">
                    <div class="panel-title">Question Title</div>

                    <form class="form-horizontal" action="startTest" method="post">

                        <c:forEach var="answer" items="${questionAnswers}">
                        <div class="form-group">
                            <label for="answer_${entry.getKey()}" class="col-sm-2 control-label">
                                    ${entry.getValue()}</label>
                            <div class="col-sm-9">
                                <div class="input-group">
                                <span class="input-group-addon">
                                    <input type="checkbox" name="correct_${entry.getKey()}" value="${entry.getValue()}"
                                           title="mark correct answer">
                                </span>
                                    <p>Answer text</p>
                                </div>
                            </div>
                        </div>
                        </c:forEach>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-9">
                                <input class="btn btn-default pull-right" type="submit" value="Next question">
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
