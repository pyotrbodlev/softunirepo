<%@ page import="app.domain.entities.Tube" %><%--
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
    <h1 class="display-3"><%=((Tube) request.getAttribute("tube")).getTitle()%>
    </h1>
    <hr/>

    <div class="embed-responsive embed-responsive-16by9" style="width: 640px; height: 360px">
        <iframe class="embed-responsive-item"
                src="https://www.youtube.com/embed/<%=((Tube)request.getAttribute("tube")).getYoutubeId()%>"
                allowfullscreen></iframe>

    </div>

    <p style="margin-top: 20px"><%=((Tube) request.getAttribute("tube")).getViews()%> views</p>

</div>
</body>
<c:import url="../templates/footer.jsp"/>
</html>

