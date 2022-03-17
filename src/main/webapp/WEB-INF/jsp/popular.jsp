<%--
  Created by IntelliJ IDEA.
  User: tangsheng geng
  Date: 2022/3/15
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/popular.css">
    <link href="${pageContext.request.contextPath}/bootstrap/bootstrap.min.css" rel="stylesheet">
    <title>Most Popular Movies</title>
</head>
<body>
<div class = "container-fluid">
    <nav class="navbar navbar-expand-sm bg-light navbar-light">

        <a class="navbar-brand ps-2 fw-bold disabled">Movie DB</a>

        <ul class="navbar-nav" id = "movieDBNavigation">
            <li class="nav-item mx-2">
                <a class="nav-link text-black"
                   href="${pageContext.request.contextPath}/homepage.html">Homepage</a>
            </li>
            <li class="nav-item dropdown mx-2">
                <a class="nav-link dropdown-toggle text-black" href="javascript:void(0)"
                   role = "button" data-bs-toggle = "dropdown" id = "sortMethod">Most Popular Movies</a>
                <ul class = "dropdown-menu">
                    <a class="dropdown-item" href = "javascript:void(0)" id = "AvgRating">
                        Sort By Average Rating
                    </a>

                    <a class="dropdown-item" href = "javascript:void(0)" id = "CountRatings">
                        Sort By Numbers of User's Ratings
                    </a>

                    <a class="dropdown-item" href = "javascript:void(0)" id = "CountTags">
                        Sort By Numbers of User's Tags
                    </a>
                </ul>
            </li>
            <li class="nav-item mx-2">
                <a class="nav-link text-black"
                   href="${pageContext.request.contextPath}/polarizing.html">Most Polarizing Movies</a>
            </li>
        </ul>

    </nav>
</div>

<div class="container">

    <div id = "header">
        <div class = "row" id = "caption">
            <h1 class = "display-1 text-center" id = "title">Top 50 Most Popular Movies</h1>
        </div>
    </div>

    <div id = "input" class = "mb-5 d-flex justify-content-end">
        <label for = "wantNum" class="form-label fst-italic fw-light">Still Want More?
            You can enter the top number of the most popular movies you want to see in the box:</label>
        <div class = input-group>
            <input class="form-control-sm border-1" list="wantNums" name="wantNumber" id="wantNum"
                   type = "number" max = "10000" min = "0" placeholder="Top">

            <datalist id="wantNums">
                <option value="10">
                <option value="30">
                <option value="50">
                <option value="100">
            </datalist>

            <button class = "btn btn-secondary" id = "submit" type = "submit">Submit</button>
        </div>
    </div>

    <div id = "content">
        <div class = "row mx-2" id = "tableHead">
            <div class = "col-4 ps-5">
                <h3>Title</h3>
            </div>
            <div class = "col-2 ps-4">
                <h3>Rating</h3>
            </div>
            <div class = "col-2 ps-4">
                <h3>Year</h3>
            </div>
            <div class = "col-4">
                <h3>Genre</h3>
            </div>
        </div>
    </div>

    <div id = "noContent" class="alert alert-primary">
        <strong>The movie you are looking for does not exist, please check again!</strong>
    </div>
</div>
<div class = "container-fluid mt-5">
    <footer class="text-center text-white" style="background-color: #f1f1f1;">
        <div class="text-center text-dark p-3">
            © 2022 Copyright:
            <a class="text-dark" href="${pageContext.request.contextPath}/homepage.html">Movie DB</a>
             &
            <a class="text-dark" href="https://github.com/UCL-COMP0022-2022-Project-2/MovieDB">Github</a>
        </div>
    </footer>
</div>
<script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popular.js"></script>
</body>
</html>
