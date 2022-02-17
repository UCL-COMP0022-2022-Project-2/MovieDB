<%--
  Created by IntelliJ IDEA.
  User: tangsheng geng
  Date: 2022/2/9
  Time: 12:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Homepage</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css">
</head>
<body>
<input id="contextPath" type="hidden" value=${pageContext.request.contextPath}/>
<div class="container">
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Sort By</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role = "button" data-bs-toggle = "dropdown">Title</a>
                        <ul class = "dropdown-menu">
                            <a class="dropdown-item" id ="titleAsc" href = "#">from A to Z</a>

                            <a class="dropdown-item" id="titleDesc" href = "#">from Z to A</a>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role = "button" data-bs-toggle = "dropdown">Rating</a>
                        <ul class = "dropdown-menu">
                            <a class="dropdown-item" id ="ratingAsc" href = "#">from low to high</a>

                            <a class="dropdown-item" id="ratingDesc" href = "#">from high to low</a>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role = "button" data-bs-toggle = "dropdown">Year</a>

                        <ul class = "dropdown-menu">
                            <a class="dropdown-item" id ="YearAsc" href = "#">from past to present</a>

                            <a class="dropdown-item" id="YearDesc" href = "#">from present to past</a>
                        </ul>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown">Genres</a>
                        <ul class="dropdown-menu">
                            <a class="dropdown-item" id ="action" href = "#">Action</a>

                            <a class="dropdown-item" id="adventure" href = "#">Adventure</a>

                            <a class="dropdown-item" id="animation" href = "#">Animation</a>

                            <a class="dropdown-item" id="biography" href = "#">Biography</a>

                            <a class="dropdown-item" id ="children" href = "#">Children</a>

                            <a class="dropdown-item" id ="comedy" href = "#">Comedy</a>

                            <a class="dropdown-item" id="drama" href = "#">Drama</a>

                            <a class="dropdown-item" id="family" href = "#">Family</a>

                            <a class="dropdown-item" id="fantasy" href = "#">Fantasy</a>

                            <a class="dropdown-item" id="film-noir" href = "#">Film-Noir</a>

                            <a class="dropdown-item" id="history" href = "#">History</a>

                            <a class="dropdown-item" id ="horror" href = "#">Horror</a>

                            <a class="dropdown-item" id="music" href = "#">Music</a>

                            <a class="dropdown-item" id="musical" href = "#">Musical</a>

                            <a class="dropdown-item" id="mystery" href = "#">Mystery</a>

                            <a class="dropdown-item" id ="romance" href = "#">Romance</a>

                            <a class="dropdown-item" id="Sci-fi" href = "#">Sci-Fi</a>

                            <a class="dropdown-item" id="sport" href = "#">Sport</a>

                            <a class="dropdown-item" id="thriller" href = "#">Thriller</a>

                            <a class="dropdown-item" id="war" href = "#">War</a>

                            <a class="dropdown-item" id="western" href = "#">Western</a>
                        </ul>
                    </li>
                </ul>
                <form class="d-flex">
                    <input class="form-control me-2" type="text" placeholder="Enter movie topic" id = "searchBar">
                    <button class="btn btn-primary" type="button" id="searchByName">Search</button>
                </form>
            </div>
        </div>
    </nav>
    <div>
        <br>
        <div>
            <button class="btn btn-dark" id="getAllPosts">
                Get all movies!
            </button>
            <br>
            <br>
        </div>

    </div>
    <div id = "content">
        <div class = "row">
            <div class = "col-md-3">
                <h3>Title</h3>
            </div>
            <div class = "col-md-2">
                <h3>Rating</h3>
            </div>
            <div class = "col-md-1">
                <h3>Year</h3>
            </div>
            <div class = "col-md-6">
                <h3>Genre</h3>
            </div>
        </div>

    </div>
</div>


<script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
<script src="https://cdn.staticfile.org/twitter-bootstrap/5.1.1/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/js/homepage.js"></script>
</body>
</html>
