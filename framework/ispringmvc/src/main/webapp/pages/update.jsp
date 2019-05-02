<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>test modelAttribute</title>
</head>
<body>
<form action="../anno/update.do" method="post">
    <input type="text" name="name" value="${user.name}">
    <input type="text" name="age" value="${user.age}">
    <input type="submit" value="提交">
</form>

</body>
</html>
