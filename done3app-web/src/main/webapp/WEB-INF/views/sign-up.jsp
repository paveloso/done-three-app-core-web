<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
    <title>asdfa</title>
</head>
<body>
<form:form action="sign-up" method="post" commandName="user">
    <form:input path="firstName"/>
    <form:input path="lastName"/>
    <form:input path="email"/>
    <form:password path="password"/>

    <input type="submit">
</form:form>
</body>

</html>