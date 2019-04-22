<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录成功</title>
</head>
<body>
    <h1>登录成功,欢迎<%= request.getSession().getAttribute("name")%></h1>
</body>
</html>
