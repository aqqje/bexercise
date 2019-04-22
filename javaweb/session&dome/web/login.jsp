<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/2
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>用户登录</title>
    <script type="text/javascript">
      function chageCheckCode(){
          var img = document.getElementById("checkcode");
          img.src = "./checkCodeServlet?"+new Date().getTime();
      }
      function init(){

          chageCheckCode();
      }
    </script>
  </head>
  <body onload="init()">
    <form action="loginServlet" method="get">
      用户名:<input type="text" name="name" /><br />
      密&nbsp;&nbsp;&nbsp;码:<input type="password" name="password"><br />
      验证码<input type="text" name="checkCode" style="width: 80px"><img id="checkcode" onclick="chageCheckCode()" src="./checkCodeServlet" alt="验证码"><br/>
      <input type="submit" value="登录">
    </form>
    <div><%= request.getAttribute("infoError")== null?"":request.getAttribute("infoError") %></div>
    <div><%= request.getAttribute("codeError")== null?"":request.getAttribute("codeError") %></div>
  </body>
</html>
