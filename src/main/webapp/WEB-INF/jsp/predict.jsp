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
<input id="contextPath" type="hidden" value=${pageContext.request.contextPath}/>
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
                    <a class="dropdown-item" href = "javascript:void(0)" id = "tagLink">
                        Based on Tags
                    </a>

                    <a class="dropdown-item" href = "javascript:void(0)" id = "genreLink">
                        Based on Genres
                    </a>

                </ul>
            </li>
        </ul>

    </nav>
</div>

<div class = "container">
    <div id = "header">
        <div class = "row" id = "caption">
            <h1 class = "display-5" id = "title">See What Kind of People Will Prefer What Kind of Movies</h1>
        </div>
    </div>

    <div class = "row d-flex align-items-center border border-4 ">
        <div class = "col-11 row" id = "box"></div>
        <div class = "col-1 text-end">
            <button class="btn btn-light m-1" id = "tagSubmit">Submit</button>
            <button class="btn btn-light m-1" id = "genreSubmit">Submit</button>
            <button class = "btn btn-light m-1" id = "clear">Clear</button>
        </div>
    </div>
    <br>

    <div id = "genreContent" class="mt-2 bg-light p-4">
        <p class="h5 py-2">Select Preferred Movie Genres</p>
        <div class="row text-center mt-3" id = "genres"></div>
    </div>

    <div id = "content" class="mt-2 bg-light p-4">
        <p class="h5 py-2">Select Preferred Movie Tags By Choosing First Letters</p>
        <div class="row text-center mb-5 bg-white">
            <div class = "letter border col" id = "A">A</div>
            <div class = "letter border col" id = "B">B</div>
            <div class = "letter border col" id = "C">C</div>
            <div class = "letter border col" id = "D">D</div>
            <div class = "letter border col" id = "E">E</div>
            <div class = "letter border col" id = "F">F</div>
            <div class = "letter border col" id = "G">G</div>
            <div class = "letter border col" id = "H">H</div>
            <div class = "letter border col" id = "I">I</div>
            <div class = "letter border col" id = "J">J</div>
            <div class = "letter border col" id = "K">K</div>
            <div class = "letter border col" id = "L">L</div>
            <div class = "letter border col" id = "M">M</div>
            <div class = "letter border col" id = "N">N</div>
            <div class = "letter border col" id = "O">O</div>
            <div class = "letter border col" id = "P">P</div>
            <div class = "letter border col" id = "Q">Q</div>
            <div class = "letter border col" id = "R">R</div>
            <div class = "letter border col" id = "S">S</div>
            <div class = "letter border col" id = "T">T</div>
            <div class = "letter border col" id = "U">U</div>
            <div class = "letter border col" id = "V">V</div>
            <div class = "letter border col" id = "W">W</div>
            <div class = "letter border col" id = "X">X</div>
            <div class = "letter border col" id = "Y">Y</div>
            <div class = "letter border col" id = "Z">Z</div>
            <div class = "letter border col" id = "#">#</div>

        </div>

        <div class="row text-center mt-3" id = "tags"></div>
    </div>
</div>
<div class="container" id="charts">

</div>
<div class="container" id="reports">

</div>
<div class="container"  id="personalities">

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
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="https://cdn.jsdelivr.net/npm/chartjs-plugin-dragdata@2.2.3/dist/chartjs-plugin-dragdata.min.js"></script>
<script src="${pageContext.request.contextPath}/js/predict.js"></script>
</body>
</html>
