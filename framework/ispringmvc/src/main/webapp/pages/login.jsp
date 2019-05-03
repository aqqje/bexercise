<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/login.do">
    用户名:<input type="text" name="name"><br>
    密码:<input type="password" name="pwd"><br>
    <input type="submit" value="登录">
</form>
</body>
</html>
