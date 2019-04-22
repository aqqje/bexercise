<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>用户修改</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/RESOURCES/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/RESOURCES/js/jquery-2.1.0.min.js" ></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/RESOURCES/css/bootstrap.min.css" ></script>
</head>
<body>
<div class="container" style="width: 400px">
    <div class="page-header">
        <div>
            <h1 align="center">修改<small>用户信息</small></h1>
        </div>
    </div>
    <div>
        <form action="${pageContext.request.contextPath}/updateUserServlet" method="post">
            <div class="form-group">
                <input type="hidden" name="id" value="${updateUser.id}">
            </div>
            <div class="form-group">
                <label for="name">用户名:</label>
                <input type="text" name="name" class="form-control" id="name" value="${updateUser.name}">
            </div>
            <div class="form-group">
                <label>性别:</label>
                <c:if test="${updateUser.gender == '男'}">
                    <label class="radio-group">
                        <input type="radio" name="gender" checked value="男">男
                        <input type="radio" name="gender" value="女">女
                    </label>
                </c:if>
                <c:if test="${updateUser.gender == '女'}">
                    <label class="radio-group">
                        <input type="radio" name="gender" value="男">男
                        <input type="radio" name="gender" checked value="女">女
                    </label>
                </c:if>
            </div>
            <div class="form-group">
                <label for="age">年龄:</label>
                <input type="text" name="age" class="form-control" value="${updateUser.age}" id="age">
            </div>
            <div class="form-group">
                <label for="address">籍贯:</label>
                <select id="address" name="address" class="form-control">
                    <c:if test="${updateUser.address == '湖南'}">
                        <option selected value="湖南">湖南</option>
                        <option value="南京">南京</option>
                        <option value="浙江">浙江</option>
                        <option value="上海">上海</option>
                        <option value="深圳">深圳</option>
                    </c:if>
                    <c:if test="${updateUser.address == '浙江'}">
                        <option value="湖南">湖南</option>
                        <option value="南京">南京</option>
                        <option selected value="浙江">浙江</option>
                        <option value="上海">上海</option>
                        <option value="深圳">深圳</option>
                    </c:if>
                    <c:if test="${updateUser.address == '上海'}">
                        <option value="湖南">湖南</option>
                        <option value="南京">南京</option>
                        <option value="浙江">浙江</option>
                        <option selected value="上海">上海</option>
                        <option value="深圳">深圳</option>
                    </c:if>
                    <c:if test="${updateUser.address == '深圳'}">
                        <option value="湖南">湖南</option>
                        <option value="南京">南京</option>
                        <option value="浙江">浙江</option>
                        <option value="上海">上海</option>
                        <option selected value="深圳">深圳</option>
                    </c:if>
                    <c:if test="${updateUser.address == '南京'}">
                        <option value="湖南">湖南</option>
                        <option selected value="南京">南京</option>
                        <option value="浙江">浙江</option>
                        <option value="上海">上海</option>
                        <option value="深圳">深圳</option>
                    </c:if>
                </select>
            </div>
            <div class="form-group">
                <label for="qq">QQ:</label>
                <input type="text" name="qq" class="form-control" id="qq" value="${updateUser.qq}">
            </div>
            <div class="form-group">
                <label for="email">邮箱:</label>
                <input type="text" name="email" class="form-control" id="email" value="${updateUser.email}">
            </div>
            <div>
                <button type="submit" class="btn btn-success">确定修改</button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
