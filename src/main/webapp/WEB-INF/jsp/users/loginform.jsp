<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <jsp:include page="/WEB-INF/jsp/common/head.jsp">
        <jsp:param name="title" value="Login"/>
    </jsp:include>
    <!-- Custom styles for this template -->
    <link type="text/css" href="/resources/css/signin.css" rel="stylesheet">
</head>

<body>

<div class="container">
    <c:if test="${isFailed}">
        <div class="panel panel-danger">
            <div class="panel-heading">
                <h3>Login Failed</h3>
            </div>
            <div class="panel-body">
                Your login attempt was not successful due to ${SPRING_SECURITY_LAST_EXCEPTION.message}
            </div>
        </div>
    </c:if>
    <form class="form-signin" role="form" action="/users/login" method="post">
        <input type="text" name="username" class="form-control" placeholder="LoginId or Email address" required autofocus>
        <input type="password" name="password" class="form-control" placeholder="Password" required>
        <label class="checkbox">
            <input type="checkbox" checked="checked" name="remember-me"> Remember me
        </label>
        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
    </form>

</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>
