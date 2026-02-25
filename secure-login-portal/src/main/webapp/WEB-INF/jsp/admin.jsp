<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${appName} - Admin</title>
</head>
<body>
    <h2>Admin Dashboard</h2>
    <p>Welcome ${pageContext.request.userPrincipal.name}</p>

    <p><a href="/home">Go to Home</a></p>

    <form method="post" action="/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">Logout</button>
    </form>
</body>
</html>
