<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${appName}</title>
</head>
<body>
    <h3>Register a new user:</h3>
    <form:form action="/register" method="post" modelAttribute="user">
        Name: <form:input path="name"/>
        <span style="color: #b00020;"><form:errors path="name"/></span>
        <br/>
        Email: <form:input path="email"/>
        <span style="color: #b00020;"><form:errors path="email"/></span>
        <br/>
        <button type="submit">Register</button>
    </form:form>

    <h3>Access as a guest:</h3>
    <form action="/register-raw" method="post">
        Name: <input type="text" name="name" value="${rawName}"/>
        <button type="submit">Submit</button>
    </form>

</body>
</html>
