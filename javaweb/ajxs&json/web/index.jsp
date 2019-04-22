<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录</title>
    <script type="text/javascript" src="resource/js/jquery-3.3.1.min.js"></script>
    <script>
      $(function(){
         // input绑定blur事件
          $("#username").blur(function(){
              /*获取值*/
             var username = $("#username").val();
             var span = $("#unspan")
              /*发送ajxs*/
              $.get("findOneUser", function(data){
                  if(data.userExsit){
                      /*不存在*/
                      span.css("color","green");
                      span.html(data.msg);
                  }else{
                      /*不存在*/
                      span.css("color","red");
                      span.html(data.msg);
                  }
              }, "json");
          });
      })
    </script>
  </head>
  <body>
    <div>
      <input type="text" id="username" name="username">
      <span id="unspan">dfdf</span>
    </div>
  </body>
</html>
