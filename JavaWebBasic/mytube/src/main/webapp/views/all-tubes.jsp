<%@ page import="java.util.List" %>
<%@ page import="app.domain.entities.Tube" %><%--
  Created by IntelliJ IDEA.
  User: Peter
  Date: 7/31/2019
  Time: 9:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Tubes</title>
    <style>
        <%@include file="../styles/create-style.css" %>
    </style>
</head>
<body>
<div>
    <h1>All Tubes</h1>
    <hr>
    <h2>Check our tubes below.</h2>
    <hr>
    <% if (request.getAttribute("tubes") == null || ((List<Tube>) request.getAttribute("tubes")).isEmpty()) {%>
    <h3>No tubes - <a href="${pageContext.request.contextPath}/create">Create some</a>!</h3>
    <% } else {%>
    <ul>
        <% for (Tube tube : ((List<Tube>) request.getAttribute("tubes"))) {%>
        <li>
            <a href="${pageContext.request.contextPath}/details?title=<%=tube.getTitle().replaceAll("\\s", "_")%>"><%=tube.getTitle()%>
            </a></li>
        <%}%>
    </ul>
    <%}%>
    <hr>
    <a href="/">Back to home.</a>
</div>
</body>
</html>
