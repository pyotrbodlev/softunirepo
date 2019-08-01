<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom box-shadow">
    <h5 class="my-0 mr-md-auto font-weight-normal"><a href="${pageContext.request.contextPath}/">MeTube v2</a></h5>
    <%
        if (request.getSession().getAttribute("isLogged") == null
                || !((boolean) request.getSession().getAttribute("isLogged"))) {
    %>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="${pageContext.request.contextPath}/register">Register</a>
    </nav>
    <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/login">Sign up</a>
    <%} else {%>
    <nav class="my-2 my-md-0 mr-md-3">
        <a class="p-2 text-dark" href="${pageContext.request.contextPath}/home">Home</a>
        <a class="p-2 text-dark" href="${pageContext.request.contextPath}/profile">Profile</a>
        <a class="p-2 text-dark" href="${pageContext.request.contextPath}/upload">Upload</a>
    </nav>
    <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/logout">LogOut</a>
    <%} %>
</div>
