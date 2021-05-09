<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Done 3 App Login</title>
</head>
<body>
<h3>Welcome to the Done 3 App</h3>

<form action="${pageContext.request.contextPath}/authenticateTheUser" method="post">

    <p>
        login: <input type="text" name="username" />
    </p>
    <p>
        password: <input type="password" name="password" />
    </p>

    <c:if test="${param.error ne null}">
        <p>
            <i style="color: red">wrong login/pass combination</i>
        </p>
    </c:if>

    <input type="submit" value="Enter" />

</form>
<div>
    <a href="${pageContext.request.contextPath}/register/form">Sign up!</a>
</div>
</body>
</html>