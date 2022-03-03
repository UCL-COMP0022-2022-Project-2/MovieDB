<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: zhuhaoyuan
  Date: 2022/3/2
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/report.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>
<div class = "container">
    <div class = "row p-2">
        <div class = "col-md-4">
            <h3>UserId</h3>
        </div>
        <div class = "col-md-4">
            <h3>Rating</h3>
        </div>
        <div class = "col-md-4">
            <h3>Tags</h3>
        </div>
    </div>
    <c:forEach items="${reports}" var="report">
        <div class="row p-2">
            <div class = "col-4">${report.userId}</div>
            <div class = "col-1">${report.rating}</div>
            <div class = "col-3">${report.ratingTimestamp}</div>
            <div class = "col-1">${report.tags}</div>
            <div class = "col-3">${report.tagTimestamp}</div>
        </div>
    </c:forEach>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

