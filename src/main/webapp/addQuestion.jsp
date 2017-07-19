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
                    <div class="panel-title">New Question Form</div>
                </div>
                <div class="panel-body">
                    <form class="form-horizontal" action="addQuestion" method="post">

                        <div class="form-group">
                            <label for="questionId" class="col-sm-2 control-label">Question ID</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="questionId" id="questionId"
                                       placeholder="Question ID">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="category" class="col-sm-2 control-label">Category</label>
                            <div class="col-sm-9">
                                <select class="form-control selectpicker" name="category"
                                        id="category">
                                    <option value="" disabled selected>Choose category</option>
                                    <c:forEach var="category" items="${categories}">
                                    <option value="${category}">${category}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="questionText" class="col-sm-2 control-label">Question Text</label>
                            <div class="col-sm-9">
                                <textarea class="form-control" name="questionText" id="questionText"
                                          placeholder="Question Text" rows="3"></textarea>
                            </div>
                        </div>

                        <c:forEach var="entry" items="${characters.entrySet()}">
                            <div class="form-group">
                                <label for="answer_${entry.getKey()}" class="col-sm-2 control-label">
                                        ${entry.getValue()}</label>
                                <div class="col-sm-9">
                                    <div class="input-group">
                                <span class="input-group-addon">
                                    <input type="checkbox" name="correct_${entry.getKey()}" value="${entry.getValue()}"
                                           title="check correct answer">
                                </span>
                                        <input type="text" class="form-control" name="answer_${entry.getKey()}"
                                               id="answer_${entry.getKey()}" placeholder="Answer ${entry.getValue()}">
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-9">
                                <input class="btn btn-default" type="submit" value="Submit">
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
