<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>spring-mvc 数据绑定</title>
</head>
<body>
<a href="param/hrefpara.do?username=aqqje&password=123456">连接数数据绑定</a>
</body>
<form action="param/formtpara.do" method="post">
    帐号名:<input type="text" name="name"><br/>
    帐号密码:<input type="password" name="password"><br/>
    帐号金额:<input type="text" name="money"><br/>
    用户姓名:<input type="text" name="user.name"><br/>
    用户年龄:<input type="text" name="user.age"><br/>
    <input type="submit" value="提交">
</form>
</html>
