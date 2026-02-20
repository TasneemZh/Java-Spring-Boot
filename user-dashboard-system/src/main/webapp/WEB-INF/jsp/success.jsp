<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${appName}</title>
</head>
<body>
    <p>Welcome ${not empty user and not empty user.name ? user.name : name}</p>
    <p style="${not empty user and not empty user.email ? '' : 'display:none;'}">
        Your email is ${user.email}
    </p>
</body>
</html>
