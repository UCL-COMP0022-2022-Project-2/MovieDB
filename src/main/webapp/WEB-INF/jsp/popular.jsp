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
    <title>Title</title>
    <title>Most Popular Movies</title>
</head>
<body>
<h1>Top 50 Most Popular Movies</h1>
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
<script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popular.js"></script>
</body>
</html>
