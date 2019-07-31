<%@ page import="app.domain.entities.Tube" %><%--
  Created by IntelliJ IDEA.
  User: Peter
  Date: 7/31/2019
  Time: 10:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Details</title>
    <style>
        <%@include file="../styles/details-style.css" %>
    </style>
</head>
<body>
<%Tube tube = ((Tube) request.getAttribute("tube"));%>
<div>
    <h1><%=tube.getTitle()%>
    </h1>
    <hr>
    <h2><%=tube.getDescription()%>
    </h2>
    <hr>
    <div class="link-container">
        <span class="link"><a href="<%=tube.getYoutubeLink()%>">Link to Video.</a></span>
        <span class="uploader"><%=tube.getUploader()%></span>
    </div>
    <hr>
    <a href="/">Back to home.</a>
</div>
</body>
</html>
