<%--
  Created by IntelliJ IDEA.
  User: tangsheng geng
  Date: 2022/2/9
  Time: 9:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Movies</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/example.css">
</head>
<body>
<%--the hidden contextPath variable to be used in javascript--%>
<input id="contextPath" type="hidden" value=${pageContext.request.contextPath}/>
<div class="container">
    <p class="display-5">
        All the results are displayed in the console.
        Press F12 to see browser console.
    </p>
    <div>
        <p>Example 1: get all movies</p>
        <button class="btn btn-dark" id="getAllPosts">
            get
        </button>
        <hr>
    </div>
    <div>
        <p>Example 2: get all movies which contains the name in the search bar</p>
        <label class="d-none" for="searchBar"></label>
        <input type="text" class="form-control form-control-lg " id="searchBar"
               placeholder="Enter the topic of the movie">
        <br>
        <button class="btn btn-dark" id="searchByName">
            search
        </button>
        <hr>
    </div>
    <div>
        <p>Example 3: list all movies with genre "War" ordered by year</p>
        <button class="btn btn-dark" id="getRequiredPosts">
            get
        </button>
        <hr>
    </div>
    <div>
        <p>Example 4: get the user report with movid id 5 </p>
        <button class="btn btn-dark" id="getReport">
            getReport
        </button>
        <hr>
    </div>
    <div>
        <p>Example 5: get the first 50 movies </p>
        <button class="btn btn-dark" id="getFirst50Movies">
            getFirst50
        </button>
        <hr>
    </div>
    <div>
        <p>Example 6: get the movies count </p>
        <button class="btn btn-dark" id="getMovieCount">
            getMovieCount
        </button>
        <hr>
    </div>
    <div>
        <p>Example 7:get the user report with movid id 5 </p>
        <a href="${pageContext.request.contextPath}/getReportsById/5/anmeeeee/scorrrre.do">
            <button class="btn btn-dark" id="newGetReport">
                getReport
            </button>
        </a>

        <hr>
    </div>
    <div>
        <p>Example 8:get the most polarizing & popular movies, first 50 entries</p>
        <button class="btn btn-dark" id="ahhhh">
            getPolarizing
        </button>
        <hr>
        <button class="btn btn-dark" id="bhhhh">
            getPopluar
        </button>
    </div>


</div>

<script src="${pageContext.request.contextPath}/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/example.js"></script>
</body>
</html>
