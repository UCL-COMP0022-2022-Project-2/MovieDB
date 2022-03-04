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
            <div class="row text-center infoLine">
                <div class="col-6 pt-2 mt-3 ps-5"><p id = "pMovieId" class="h5">Movie: ${movieName}</p></div>
                <!--
                <div class="display-5">Movie Id: ${movieId}</div>
                <div class="display-5">Movie Name: ${movieName}</div>
                <div class="display-5">Movie Rating: ${movieRating}</div>
                -->
                <div class="col-6 pt-2 mt-3 pe-5"><p id = "pAverageRating" class="h5">Average Rating: ${movieRating}</p></div>
            </div>
        </div>
    </div>

    <nav class="navbar navbar-expand-lg navbar-light sticky-top pt-3 pb-2 border">
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
        <div class="row m-2 py-3 itemRow">
            <div class = "col-2"></div>
            <div class = "col-1 text-center">${report.userId}</div>
            <div class = "col-1"></div>
            <div class = "col-1 ratingString text-center">${report.rating}</div>
            <div class = "col-2 text-muted small">${report.ratingTimestamp}</div>
            <div class = "col-1 text-capitalize">${report.tags}</div>
            <div class = "col-3 text-muted small">${report.tagTimestamp}</div>
        </div>
    </c:forEach>
    </div>
</div>
<script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/report.js"></script>
</body>
</html>

