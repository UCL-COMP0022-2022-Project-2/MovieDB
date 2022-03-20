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
    <title>User Rating Report</title>
</head>
<body>
<div class = "container-fluid">
    <nav class="navbar navbar-expand-sm bg-light navbar-light">

        <a class="navbar-brand ps-2 fw-bold" disabled>Movie DB</a>

        <ul class="navbar-nav" id = "movieDBNavigation">
            <li class="nav-item mx-2">
                <a class="nav-link text-black"
                   href="${pageContext.request.contextPath}/homepage.html">Homepage</a>
            </li>
            <li class="nav-item mx-2">
                <a class="nav-link text-black"
                   href="${pageContext.request.contextPath}/popular.html">Most Popular Movies</a>
            </li>
            <li class="nav-item mx-2">
                <a class="nav-link text-black"
                   href="${pageContext.request.contextPath}/polarizing.html">Most Polarizing Movies</a>
            </li>
            <li class = "nav-item mx-2">
                <a class = "nav-link text-black"
                   href = "${pageContext.request.contextPath}/predict.html">Predict Who Will Like the Movie</a>
            </li>
        </ul>

    </nav>
</div>

<div class = "container">

    <div id = "header">
        <div id = "caption">
            <h1 class = "display-3 text-center">User Rating Report</h1>
            <div class="row text-center infoLine">
                <div class="col-6 pt-2 mt-3 ps-5">
                    <p id = "pMovieId" class="h5">Movie: ${movieName}</p>
                </div>
                <div class="col-6 pt-2 mt-3 pe-5">
                    <p id = "pAverageRating" class="h5">Average Rating: ${movieRating}</p>
                </div>
            </div>

            <div class="border border-3 p-4 bg-light mt-3" id = "predictBox">
                <div class = "row">
                    <div class="col  p-4">
                        <p class = "h4">Predict Rating for this Movie</p>
                    </div>
                </div>

                <div class = "row mt-3 mb-3 ratingLine">
                    <div class="col-3 ps-5">
                        <i class = "h5 pt-2">Predict Rating: </i>
                    </div>
                    <div class = "col-1">
                        <i id = "predictRating" class = "h5 pt-2"></i>
                        <p id = "movieId" class="visually-hidden">${movieId}</p>
                    </div>
                </div>
                <div class="text-center pt-3 ratingLine" >
                    <img src="https://upload.wikimedia.org/wikipedia/commons/thumb/b/b6/Image_created_with_a_mobile_phone.png/640px-Image_created_with_a_mobile_phone.png"
                         alt="pic" class = "img-fluid">
                </div>
            </div>


        </div>
    </div>

    <nav class="navbar navbar-expand-lg navbar-light sticky-top pt-3 pb-2 border bg-white">
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
<div class = "container-fluid mt-5">
    <footer class="text-center text-white" style="background-color: #f1f1f1;">
        <div class="text-center text-dark p-3">
            © 2022 Copyright:
            <a class="text-dark" href="https://github.com/UCL-COMP0022-2022-Project-2/MovieDB" style="text-decoration: none">
                COMP0022 Team2 MovieDB</a>
        </div>
    </footer>
</div>
<script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/report.js"></script>
</body>
</html>

