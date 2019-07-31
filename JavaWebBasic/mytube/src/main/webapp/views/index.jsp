<%--
  Created by IntelliJ IDEA.
  User: Peter
  Date: 7/31/2019
  Time: 8:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home page</title>
    <style><%@include file="../styles/index-style.css"%></style>
</head>
<body>
<div>
    <h1>Welcome to MeTube!</h1>
    <hr>
    <h2>Cool app in beta version</h2>
    <hr>
    <div class="button-container">
        <span class="create"><a href="${pageContext.request.contextPath}/create"><button>Create Tube</button></a></span>
        <span class="all"><a href="${pageContext.request.contextPath}/all"><button>All Tubes</button></a></span>
    </div>
</div>
</body>
</html>
