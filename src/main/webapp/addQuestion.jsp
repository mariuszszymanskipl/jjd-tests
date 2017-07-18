<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <jsp:include page="partials/meta.jsp"/>
    <title>JJD Test</title>
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
                    <form class="form-horizontal" action="#" method="post">

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
                                    <%--<c:forEach var="category" items="${categories}">--%>
                                    <%--<option value="${category}">${category}</option>--%>
                                    <%--</c:forEach>--%>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="questionText" class="col-sm-2 control-label">Question Text</label>
                            <div class="col-sm-9">
                                <textarea class="form-control" rows="3" name="questionText" id="questionText"
                                          placeholder="Question Text">
                                </textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="answerA" class="col-sm-2 control-label">A</label>
                            <div class="col-sm-9">
                                <div class="input-group">
                                <span class="input-group-addon">
                                    <input type="checkbox" name="correctA" title="check correct answer">
                                </span>
                                    <input type="text" class="form-control" name="answerA" id="answerA"
                                           placeholder="Answer A">
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="answerB" class="col-sm-2 control-label">B</label>
                            <div class="col-sm-9">
                                <div class="input-group">
                                <span class="input-group-addon">
                                    <input type="checkbox" name="correctB" title="check correct answer">
                                </span>
                                    <input type="text" class="form-control" name="answerB" id="answerB"
                                           placeholder="Answer B">
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="answerC" class="col-sm-2 control-label">C</label>
                            <div class="col-sm-9">
                                <div class="input-group">
                                <span class="input-group-addon">
                                    <input type="checkbox" name="correctC" title="check correct answer">
                                </span>
                                    <input type="text" class="form-control" name="answerC" id="answerC"
                                           placeholder="Answer C">
                                </div>
                            </div>
                        </div>
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
