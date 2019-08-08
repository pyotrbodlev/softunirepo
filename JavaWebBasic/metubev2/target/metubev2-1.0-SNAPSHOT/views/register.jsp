<%--
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
    <title>Register</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body class="d-flex flex-column h-100">
<header>
    <c:import url="../templates/header.jsp"/>
    <style>
        #my-btn {
            margin: 30px 30px;
        }

        input[type=text], input[type=password], input[type=email] {
            padding: 12px 20px;
            margin: 8px 0;
            border: 1px solid #ccc;
            border-radius: 4px;
        }
    </style>
</header>
<!-- Begin page content -->
<div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
    <h1 class="display-4">Register</h1>
    <hr/>
    <form action="${pageContext.request.contextPath}/register" method="post">
        <p class="lead">Username</p>
        <input type="text" name="username" placeholder="Enter your username...">
        <p class="lead">Password</p>
        <input type="password" name="password" placeholder="Enter your password..." id="password">
        <p class="lead">Confirm password</p>
        <input type="password" name="confirm_password" placeholder="Confirm your password..." id="confirm_password">
        <p class="lead">Email</p>
        <input type="email" name="email" placeholder="Enter your email...">

        <br/><input class="btn btn-outline-primary" type="submit" value="Sign in" id="my-btn">
    </form>
</div>
<script>
    var password = document.getElementById("password")
        , confirm_password = document.getElementById("confirm_password");

    function validatePassword() {
        if (password.value !== confirm_password.value) {
            confirm_password.setCustomValidity("Passwords Don't Match");
        } else {
            confirm_password.setCustomValidity('');
        }
    }

    password.onchange = validatePassword;
    confirm_password.onkeyup = validatePassword;
</script>
</body>
<c:import url="../templates/footer.jsp"/>
</html>
