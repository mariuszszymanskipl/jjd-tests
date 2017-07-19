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
        <p>${result} / ${testSize}</p>
        <br>
        <a type="button" class="btn btn-default btn-lg" href="startTest">Main page</a>
    </div>
</div>
</body>
</html>
