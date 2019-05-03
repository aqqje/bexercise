<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>响应数据和结果视图</title>
</head>

<script type="text/javascript" src="js/jquery.min.js"></script>
<script>
    $(function(){
        $("#bt1").click(function(){
            $.ajax({
                type: "post",
                url: "${pageContext.request.contextPath}/user/requestBody.do",
                contentType: "application/json;charset=utf-8",
                data: '{"name":"aqqje", "gender":"男", "age":20}',
                dataType: "json",
                success: function(data){
                    $("#user").text(data.name+data.age+data.gender);
                }
            });
        });
    });
</script>
<body>
<hr>

<a href="user/string.do">string</a>
<hr>

<a href="user/modelAndView.do">modelAndView</a>
<hr>

<a href="user/idvoid.do">idvoid</a>
<hr>

<a href="user/forwardOrRedirect.do">forwardOrRedirect</a>
<hr>

<button id="bt1">点我发送 ajax</button>
<h1 id="user"></h1>
</body>
</html>
