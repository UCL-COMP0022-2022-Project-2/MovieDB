<%--
  Created by IntelliJ IDEA.
  User: zhuhaoyuan
  Date: 2022/3/19
  Time: 18:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/predict.css">
    <link href="${pageContext.request.contextPath}/bootstrap/bootstrap.min.css" rel="stylesheet">
    <title>Predict Who Will Like the Movie</title>
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
            <li class="nav-item dropdown mx-2">
                <a class="nav-link dropdown-toggle text-black" href="javascript:void(0)"
                   role = "button" data-bs-toggle = "dropdown">Predict Who Will Like the Movie</a>
                <ul class = "dropdown-menu">
                    <a class="dropdown-item" href = "javascript:void(0)">
                        Based on Tags
                    </a>

                    <a class="dropdown-item" href = "javascript:void(0)">
                        Based on Genres
                    </a>

                </ul>
            </li>
        </ul>

    </nav>
</div>
<div class = "container-fluid mt-5">
    <footer class="text-center text-white" style="background-color: #f1f1f1;">
        <div class="text-center text-dark p-3">
            © 2022 Copyright:
            <a class="text-dark" style="text-decoration: none" href="https://github.com/UCL-COMP0022-2022-Project-2/MovieDB">
                COMP0022 Team2 MovieDB</a>
        </div>
    </footer>
</div>

<script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/predict.js"></script>
</body>
</html>
