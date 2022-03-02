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
    <title>Title</title>
</head>
<body>
<input id = "reports" value = "$(reports)" type = "hidden">


<script src="${pageContext.request.contextPath}/jquery/jquery.js"></script>
<script>
    var reports = $("#reports").val();
    console.log(reports);
</script>
</body>
</html>

