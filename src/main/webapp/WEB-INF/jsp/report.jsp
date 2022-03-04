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
    <link href="${pageContext.request.contextPath}/bootstrap/bootstrap.min.css" rel="stylesheet">
    <title>Title</title>
</head>
<body>

<div class = "container">

    <div id = "header">
        <div id = "caption">
            <h1 class = "display-3 text-center">User Rating Report</h1>
            <div class="row">
                <div class="col-6 text-end pt-2 ps-2 pe-5 mt-3"><p id = "pMovieId">Movie Id: </p></div>
                <div class="col-6 px-2 pt-2 mt-3"><p id = "pAverageRating">Average Rating: </p></div>
            </div>
        </div>
    </div>

    <nav class="navbar navbar-expand-lg navbar-light bg-light sticky-top pt-3 pb-2">
            <div class="collapse navbar-collapse">
                <div class="navbar-nav">
                    <div class = "nav-item text-dark h3" id = "userId">UserId</div>
                    <div class = "nav-item text-dark h3" id = "rating">Rating</div>
                    <div class = "nav-item text-dark h3" id = "tags">Tags</div>
                </div>
            </div>
    </nav>


    <div id = "reportTable">
    <c:forEach items="${reports}" var="report">
        <div class="row p-2">
            <div class = "col-2 kong"></div>
            <div class = "col-2">${report.userId}</div>
            <div class = "col-1 ratingString">${report.rating}</div>
            <div class = "col-2 text-muted small">${report.ratingTimestamp}</div>
            <div class = "col-1 text-capitalize">${report.tags}</div>
            <div class = "col-3 text-muted small">${report.tagTimestamp}</div>
        </div>
        <hr>
    </c:forEach>
    </div>
</div>
<script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/report.js"></script>
</body>
</html>

