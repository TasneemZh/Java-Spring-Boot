<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${appName}</title>
</head>
<body>
    <% if (request.getParameter("error") != null) { %>
        <p style="color: #b91c1c;">Invalid username or password.</p>
    <% } %>
    <% if (request.getParameter("logout") != null) { %>
        <p style="color: #166534;">You have been logged out.</p>
    <% } %>

    <form method="post" action="/login">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        Username: <input type="text" name="username"/>
        Password: <input type="password" name="password"/>
        <button type="submit">Login</button>
    </form>
    <div style="margin-top: 10px; padding: 10px; border: 1px solid #93c5fd; background-color: #eff6ff; width: fit-content;">
        <strong>Test User</strong><br/>
        Username: <code>${testUsername}</code><br/>
        Password: <code>${testPassword}</code>
    </div>

    <a href="/oauth2/authorization/google">
        Login with Google
    </a>
</body>
</html>
