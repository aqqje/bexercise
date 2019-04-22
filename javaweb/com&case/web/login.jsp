<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <title>管理员登录</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/RESOURCES/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/RESOURCES/js/jquery-2.1.0.min.js" ></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/RESOURCES/css/bootstrap.min.css" ></script>
    <script>
      function refreshCode(){
          var vcode = document.getElementById("vcode");
          vcode.src = "${pageContext.request.contextPath}/checkCodeServlet?id="+new Date().getTime();
      }
      function init(){
          refreshCode();
      }
    </script>
  </head>
  <body onload="init()">
    <div class="container" style="width: 400px;" >

      <%--logo--%>
      <div class="header">
        <h1 align="center">管理员登录</h1>
      </div>

      <%--登录表单--%>
      <div class="login-form-info">
        <form action="${pageContext.request.contextPath}/loginServlet" method="get">
          <div class="form-group">
            <label for="username">用户名:</label>
            <input type="text" class="form-control" name="username" id="username" placeholder="请输入用户名">
          </div>
          <div class="form-group">
            <label for="password">密&nbsp;&nbsp;码:</label>
            <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
          </div>
          <div class="form-inline">
            <label for="vcode">验证码:</label>
            <input type="text" class="form-control" name="verifycode" id="verifycode" placeholder="请输入验证码" style="">
            <a href="javascript:refreshCode()">
              <img src="${pageContext.request.contextPath}/checkCodeServlet" alt="验证码" title="看不清点击刷新" id="vcode">
            </a>
          </div>
          <div class="nav nav-list"><p class="divider"></p></div>
          <div style="text-align: center;">
            <button type="submit" class="btn btn-primary" style="width: 150px">登&nbsp;&nbsp;录</button>
          </div>
        </form>
      </div>

      <%--错误警告框--%>
      <c:if test="${loginErrorMsg != null || loginErrorMsg.lenght > 0}">
        <div class="alert alert-warning alert-dismissible" role="alert">
          <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span></button>
          <strong>登录失败!</strong> ${loginErrorMsg}
        </div>
      </c:if>
    </div>
  </body>
</html>
