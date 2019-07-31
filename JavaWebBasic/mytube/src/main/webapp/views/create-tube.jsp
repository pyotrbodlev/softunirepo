<%--
  Created by IntelliJ IDEA.
  User: Peter
  Date: 7/31/2019
  Time: 9:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Tube</title>
    <style><%@include file="../styles/create-style.css"%></style>
</head>
<body>
<div>
    <h1>Create Tube!</h1>
    <hr>
    <form action="${pageContext.request.contextPath}/create" method="post">
        <label>
            Title
            <input type="text" name="title">
        </label>
        <hr>
        <label>
            Description
            <input type="text" name="description">
        </label>
        <hr>
        <label>
            YouTube Link
            <input type="text" name="link">
        </label>
        <hr>
        <label>
            Uploader
            <input type="text" name="uploader">
        </label>
        <hr>
        <input type="submit" value="Create Tube">
    </form>

    <a href="/">Back to home.</a>
</div>
</body>
</html>
