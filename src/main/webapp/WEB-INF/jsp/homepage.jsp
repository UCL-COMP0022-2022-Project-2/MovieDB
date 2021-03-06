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
<div class="container">
    <div id = "header">
        <div class = "row" id = "caption">
            <h1 class = "display-1 text-center">Movie DB</h1>
        </div>
    </div>
    <div class = "searchBox bg-light p-3 border">
        <h5>Select By</h5>

        <div class = "row py-2">
            <div class=" input-group">
                <span class="input-group-text">Title</span>
                <input class="form-control"
                       type="text"
                       placeholder="Please Enter the Title of the Movie"
                       id = "searchBar">
            </div>
        </div>
        <div class="row pb-2">
            <div class = "col-4 mt-2">
                <div class="input-group">
                    <span class="input-group-text">Rating (0-5)</span>
                    <select class="form-select" id = "ratingStart">
                        <option value = "none" selected disabled hidden>From</option>
                        <option>0</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                        <option class = "bg-light">None</option>
                    </select>
                    <select class="form-select" id = "ratingEnd">
                        <option value = "none" selected disabled hidden>To</option>
                        <option>0</option>
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                        <option class = "bg-light">None</option>
                    </select>
                </div>
            </div>
            <div class = "col-3 mt-2">
                <div class="input-group">
                    <span class="input-group-text">Published Year</span>
                    <input class="form-control" type = "number" placeholder="YYYY"
                           min = "1902" max = "2018" id = "yearNum">
                </div>
            </div>
            <div class = "col-3 mt-2">
                <div class="input-group">
                    <span class="input-group-text">Genres</span>
                    <select class="form-select" id = "genreType">
                        <option value = "none" selected disabled hidden>Select From</option>
                        <option>Action</option>
                        <option>Adventure</option>
                        <option>Animation</option>
                        <option>Children</option>
                        <option>Comedy</option>
                        <option>Drama</option>
                        <option>Documentary</option>
                        <option>Family</option>
                        <option>Fantasy</option>
                        <option>Film-Noir</option>
                        <option>History</option>
                        <option>Horror</option>
                        <option>IMAX</option>
                        <option>Music</option>
                        <option>Musical</option>
                        <option>Mystery</option>
                        <option>Romance</option>
                        <option>Sci-Fi</option>
                        <option>Sport</option>
                        <option>Thriller</option>
                        <option>War</option>
                        <option>Western</option>
                        <option class = "bg-light">None</option>
                    </select>
                </div>
            </div>
            <div class="col-2">
                <button class="btn btn-secondary float-end mt-2" id="searchByName">
                    Search
                </button>
            </div>
        </div>
    </div>
    <br>
    <nav class="navbar navbar-expand-sm bg-light navbar-light sticky-top border">
        <div class="container-fluid">
            <a class="navbar-brand ps-2" href="javascript:void(0)">Sort By</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item dropdown" id = "titleNav">
                        <a class="nav-link dropdown-toggle" href="javascript:void(0)"
                           role = "button" data-bs-toggle = "dropdown">Title</a>
                        <ul class = "dropdown-menu">
                            <a class="dropdown-item" id ="titleAsc" href = "javascript:void(0)">from A to Z</a>

                            <a class="dropdown-item" id="titleDesc" href = "javascript:void(0)">from Z to A</a>
                        </ul>
                    </li>
                    <li class="nav-item dropdown" id = "ratingNav">
                        <a class="nav-link dropdown-toggle" href="javascript:void(0)"
                           role = "button" data-bs-toggle = "dropdown">Rating</a>
                        <ul class = "dropdown-menu">
                            <a class="dropdown-item" id ="ratingAsc" href = "javascript:void(0)">from low to high</a>

                            <a class="dropdown-item" id="ratingDesc" href = "javascript:void(0)">from high to low</a>
                        </ul>
                    </li>
                    <li class="nav-item dropdown" id = "yearNav">
                        <a class="nav-link dropdown-toggle" href="javascript:void(0)"
                           role = "button" data-bs-toggle = "dropdown">Year</a>

                        <ul class = "dropdown-menu">
                            <a class="dropdown-item" id ="yearAsc" href = "javascript:void(0)">from past to present</a>

                            <a class="dropdown-item" id="yearDesc" href = "javascript:void(0)">from present to past</a>
                        </ul>
                    </li>

                </ul>
                <form class="d-flex">
                    <button class="btn btn-secondary mt-2" type="button" id="getAllPosts">Get All Movies</button>
                </form>

            </div>
        </div>
    </nav>
    <br>

    <!--<button id = "check">Check</button>-->

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

    <div id = "footer" class="mt-5">
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <li class="page-item" id = "firstPage">
                    <a class="page-link text-dark" href="javascript:void(0)">Return to the Beginning</a>
                </li>
                <li class="page-item" id = "previousTenPages">
                    <a class="page-link text-dark" href="javascript:void(0)" aria-label="Previous">
                        <span aria-hidden="true">&laquo</span>
                    </a>
                </li>

                <li class="page-item" id = "nextTenPages">
                    <a class="page-link text-dark" href="javascript:void(0)" aria-label="Next">
                        <span aria-hidden="true">&raquo</span>
                    </a>
                </li>
                <li class="page-item" id = "finalPage">
                    <a class="page-link text-dark" href="javascript:void(0)">Return to the End</a>
                </li>
            </ul>
        </nav>
    </div>
</div>

<div class = "container-fluid mt-5">
    <footer class="text-center text-white" style="background-color: #F1F1F1;">
        <div class="text-center text-dark p-3">
            ?? 2022 Copyright:
            <a class="text-dark" style="text-decoration: none"
            href="https://github.com/UCL-COMP0022-2022-Project-2/MovieDB">COMP0022 Team2 MovieDB</a>
        </div>
    </footer>
</div>


<script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/homepage.js"></script>
</body>
</html>
