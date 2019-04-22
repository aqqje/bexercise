<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/RESOURCES/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/RESOURCES/js/jquery-2.1.0.min.js" ></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/RESOURCES/css/bootstrap.min.css" ></script>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-xs-6 col-xs-offset-2">
                <h3 class="text-primary">欢迎您,管理员 ${loginUser.username}</h3>
            </div>
        </div>

        <div class="row">
            <div class="col-xs-6 col-xs-offset-3">
                <h1 class="text-warning" align="center">
                    <a href="${pageContext.request.contextPath}/beanPageServlet">所有用户信息</a>
                </h1>
            </div>
        </div>
    </div>
</body>
</html>
