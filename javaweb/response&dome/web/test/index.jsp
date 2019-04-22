<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2019/4/1
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">

  function change(){
      var img = document.getElementById("checkCode");
      // 绑定点击事件
      // 增加时间戳
      var data = new Date().getTime()
      img.src = "/servlet/CheckCodeServlet?"+data;
  }

</script>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body onload="change()">
  <%--<a href="../responseDome2">dome2</a>--%>
  <img id="checkCode" onclick="change()" src="/dome/CheckCodeServlet">
  <input type="button" value="换一张" onclick="change()" />
  </body>
</html>
