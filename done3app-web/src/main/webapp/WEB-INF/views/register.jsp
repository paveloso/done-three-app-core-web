<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Done 3 App Login</title>
</head>
<body>
<h3>New user registration</h3>

<form:form action="${pageContext.request.contextPath}/register/do" modelAttribute="appUser">

    <c:if test="${registrationError != null}">
        <div>
            ${registrationError}
        </div>
    </c:if>

    <form:input path="email" placeholder="email" />

    <form:password path="password" placeholder="password"/>

    <form:password path="matchingPassword" placeholder="confirm password"/>

    <form:input path="firstName" placeholder="first name"/>

    <form:input path="lastName" placeholder="last name"/>

    <button type="submit">Register</button>

</form:form>
</body>
</html>