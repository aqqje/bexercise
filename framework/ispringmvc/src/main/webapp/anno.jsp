<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>spring-mvc 常用注解</title>
</head>
<body>
<a href="anno/requestParam.do?username=aqqje&password=13246">requestParam</a><br>
<hr>

<form action="anno/requestBody.do" method="post">
    用户名:<input type="text" name="name"/><br>
    密码:<input type="password" name="password"/><br>
    <input type="submit" value="提交">
</form>

<hr>

<a href="anno/pathVariable/100.do">pathVariable</a>

<hr>

<a href="anno/requestHeader.do">requestHeader</a>

<hr>
<a href="anno/cookieValue.do">cookieValue</a>
<hr>

<a href="anno/toUpatePage.do?name=aqqje">toUpatePage</a>

<hr>

<a href="anno/putSession.do">putSession</a>

<hr>

<a href="anno/getSession.do">getSession</a>

<hr>

<a href="anno/removeSession.do">removeSession</a>

<hr>
</body>

</html>
