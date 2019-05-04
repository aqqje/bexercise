<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>spring mvc restful</title>
</head>
<h1>GET</h1><hr>

<h3>a 标签:<a href="${ctx}/user/user/1.do">restGet</a></h3><br><hr>
<h3>form rest表单:
    <form action="${ctx}/user/user/1.do" method="get">
        <input type="hidden" name="_method" value="GET">
        <input type="submit" value="restGet">
    </form>
</h3><br><hr>
<h1>POST</h1><hr>
<h3>form 普通表单:
    <form action="${ctx}/user/user.do" method="post">
        <input type="submit" value="restPost">
    </form>
</h3><hr>
<h3>form rest表单:
    <form action="${ctx}/user/user.do" method="post">
        <input type="hidden" name="_method" value="POST">
        <input type="submit" value="restPost">
    </form>
</h3><hr>


<h1>PUT</h1><hr>
<h3>form rest表单:
    <form action="${ctx}/user/user/2.do" method="post">
        <input type="hidden" name="_method" value="PUT">
        <input type="submit" value="restPut">
    </form>
</h3><hr>

<h1>DELTE</h1><hr>
<h3>form rest表单:
    <form action="${ctx}/user/user/3.do" method="post">
        <input type="hidden" name="_method" value="DELETE">
        <input type="submit" value="restDelete">
    </form>
</h3><hr>

</body>
</html>
