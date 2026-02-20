<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${appName}</title>
</head>
<body>
    <h2>Dashboard</h2>
    <p>Logged in as: ${user.name} (${user.email})</p>

    <h3>Search User By Email</h3>
    <form action="/search" method="get">
        <input type="text" name="keyword" value="${keyword}" placeholder="Enter email"/>
        <button type="submit">Search</button>
    </form>

    <p style="${not empty searchMessage ? '' : 'display:none;'}">${searchMessage}</p>

    <div style="${not empty searchedUser ? '' : 'display:none;'}">
        <h4>Search Result</h4>
        <p>Name: ${searchedUser.name}</p>
        <p>Email: ${searchedUser.email}</p>
    </div>
</body>
</html>
