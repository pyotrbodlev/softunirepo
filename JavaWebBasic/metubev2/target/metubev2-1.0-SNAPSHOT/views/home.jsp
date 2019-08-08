<%@ page import="app.domain.entities.User" %><%--
  Created by IntelliJ IDEA.
  User: Peter
  Date: 8/1/2019
  Time: 9:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>MeTube v2</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body class="d-flex flex-column h-100">
<header>
    <c:import url="../templates/header.jsp"/>
</header>

<!-- Begin page content -->
<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">Welcome <%=((User) request.getSession().getAttribute("loggedUser")).getUsername()%>
    </h1>
    <%
        if (((User) request.getSession().getAttribute("loggedUser")).getTubes() != null &&
                !((User) request.getSession().getAttribute("loggedUser")).getTubes().isEmpty()) {
    %>
    <div class="album py-5 bg-light">
        <div class="container">

            <div class="row">
                <c:import url="../templates/thumbnail.jsp"/>
            </div>
        </div>
    </div>
    <%} else {%>
    <p>You don't have tubes :(<a href="${pageContext.request.contextPath}/upload">Upload</a> some!</p>
    <%}%>
</div>
</body>
<c:import url="../templates/footer.jsp"/>
</html>

