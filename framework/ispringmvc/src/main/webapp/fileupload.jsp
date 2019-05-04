<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
文件上传传统方式
    <form action="user/fileUpload1.do" enctype="multipart/form-data" method="post">
        上传文件:<input name="file" type="file"><br>
        <input type="submit"  value="提交">
    </form>
<hr>
spring mvc 文件上传方式:
<form action="user/fileUpload2.do" enctype="multipart/form-data" method="post">
    <%--name: 属性名指定必须与 controller 的形参一致--%>
    上传文件:<input name="uploat" type="file"><br>
    <input type="submit"  value="提交">
</form>
<hr>
跨服务器文件上传
<form action="user/fileUpload3.do" method="post" enctype="multipart/form-data">
    名称：<input type="text" name="picname"/><br/>
    图片：<input type="file" name="uploadFile"/><br/>
    <input type="submit" value="上传"/>
</form>
</body>
</html>
