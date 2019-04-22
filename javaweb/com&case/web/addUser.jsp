<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加新用户</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/RESOURCES/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/RESOURCES/js/jquery-2.1.0.min.js" ></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/RESOURCES/css/bootstrap.min.css" ></script>
</head>
<body>
    <div class="container" style="width: 400px">
        <div class="page-header">
            <div>
                <h1 align="center">添加<small>新用户</small></h1>
            </div>
        </div>
        <div>
            <form action="${pageContext.request.contextPath}/addUserServlet" method="post">
                <div class="form-group">
                    <label for="name">用户名:</label>
                    <input type="text" name="name" class="form-control" id="name" placeholder="请输入用户名">
                </div>
                <div class="form-group">
                    <label for="gender1">性别:</label>
                    <label class="radio-group">
                        <input type="radio" name="gender" id="gender1" checked value="男">男
                    </label>
                    <label class="radio-group">
                        <input type="radio" name="gender" id="gender2" value="女">女
                    </label>
                </div>
                <div class="form-group">
                    <label for="age">年龄:</label>
                    <input type="text" name="age" class="form-control" id="age" placeholder="请输入年龄">
                </div>
                <div class="form-group">
                    <label for="address">籍贯:</label>
                    <select id="address" name="address" class="form-control">
                        <option value="湖南">湖南</option>
                        <option value="浙江">浙江</option>
                        <option value="上海">上海</option>
                        <option value="深圳">深圳</option>
                        <option value="南京">南京</option>
                    </select>
                </div>
                <div class="form-group">
                    <label for="qq">QQ:</label>
                    <input type="text" name="qq" class="form-control" id="qq" placeholder="请输入QQ">
                </div>
                <div class="form-group">
                    <label for="email">邮箱:</label>
                    <input type="text" name="email" class="form-control" id="email" placeholder="请输入邮箱">
                </div>
                <div>
                    <button type="submit" class="btn btn-success">添加</button>
                    <button type="reset" class="btn btn-warning">重置</button>
                    <button href="${pageContext.request.contextPath}/userListServlet" type="submit" class="btn btn-info">返回</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
