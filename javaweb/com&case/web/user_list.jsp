<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>所有用户</title>
    <!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/RESOURCES/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/RESOURCES/js/jquery-2.1.0.min.js" ></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/RESOURCES/css/bootstrap.min.css" ></script>
    <script>
        function deleteById(id){
            if(confirm("您确定要删除吗？")){
                location.href="${pageContext.request.contextPath}/deleteUserServlet?id="+id;
            }
        }
        function findOneById(id){
            location.href="${pageContext.request.contextPath}/findOneByIdServlet?id="+id;
        }
        window.onload = function(){
            document.getElementById("deleteSelected").onclick = function(){
                var flag = false;
                var uids = document.getElementsByName("uid");
                for(var i = 0; i < uids.length; i++){
                    if(uids[i].checked){
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    if(confirm("你确定要删除选择的条目吗?")){
                        document.getElementById("formList").submit();
                    }
                }else{
                    alert("亲,请选择!");
                }
            }
            document.getElementById("firstCb").onclick = function(){
               var uids = document.getElementsByName("uid");
               for(var i = 0; i < uids.length; i++){
                   uids[i].checked = this.checked;
               }
            }
        }
    </script>
</head>
<body>
    <div class="container">
        <div class="page-header">
            <div>
                <h1 align="center">所有<small>用户信息</small></h1>
            </div>
        </div>
        <%--条件查询--%>
        <div style="float: left">
            <form class="form-inline">
                <div class="form-group" action="${pageContext.request.contextPath}/beanPageServlet" method="post">
                    <label for="name">姓名:</label>
                    <input type="text" class="form-control" name="name" id="name" value="${condation.name[0]}" placeholder="输入要查询的姓名">
                </div>
                <div class="form-group">
                    <label for="address">籍贯:</label>
                    <input type="text" class="form-control" name="address" id="address" value="${condation.address[0]}" placeholder="输入要查询的籍贯">
                </div>
                <div class="form-group">
                    <label for="email">邮箱:</label>
                    <input type="email" class="form-control" name="email" id="email" value="${condation.email[0]}" placeholder="输入要查询的邮箱">
                </div>
                <button type="submit" class="btn btn-info">查 询</button>
            </form>
        </div>
        <%--添加与删除选中--%>
        <div style="float: right">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/addUser.jsp">添加联系人</a>
            <a class="btn btn-danger" href="javascript:void(0)" id="deleteSelected">删除选中</a>
        </div>
        <div style="clear: both"></div>
        <div>
            <form id="formList" action="${pageContext.request.contextPath}/deleteSelectedServlet" method="post">
                <table class="table table-hover table-bordered" style="text-align: center">
                <thead>
                <tr class="success">
                    <td><input type="checkbox" id="firstCb"></td>
                    <td>编号</td>
                    <td>姓名</td>
                    <td>性别</td>
                    <td>年龄</td>
                    <td>籍贯</td>
                    <td>QQ</td>
                    <td>邮箱</td>
                    <td>操作</td>
                </tr>
                </thead>
                <tbody>
                <c:if test="${pb.list != null || pb.list.size > 0}">
                    <c:forEach varStatus="vs" items="${pb.list}" var="user">
                        <tr>
                            <td><input type="checkbox" name="uid"  value="${user.id}"></td>
                            <td>${vs.index + 1}</td>
                            <td>${user.name}</td>
                            <td>${user.gender}</td>
                            <td>${user.age}</td>
                            <td>${user.address}</td>
                            <td>${user.qq}</td>
                            <td>${user.email}</td>
                            <td>
                                <a class="btn btn-info" href="javascript:findOneById(${user.id})" role="button">修改</a>
                                <a class="btn btn-danger" href="javascript:deleteById(${user.id})" role="button">删除</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${pb.list == null}">
                    <tr>
                        <td style="text-align: center" colspan="9">
                            <span>暂无数据!</span>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
            </form>
        </div>
        <%--分页--%>
        <div aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pb.currentPage <= 1}">
                    <li class="disabled">
                </c:if>
                <c:if test="${pb.currentPage > 1}">
                <li>
               </c:if>
                    <a href="${pageContext.request.contextPath}/beanPageServlet?currentPage=${pb.currentPage - 1}&rows=5&name=${condation.name[0]}&address=${condation.address[0]}&email=${condation.email[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach end="${pb.totalPage}" begin="1" step="1" var="page">
                    <c:if test="${pb.currentPage == page}">
                        <li class="active"><a href="${pageContext.request.contextPath}/beanPageServlet?currentPage=${page}&rows=5&name=${condation.name[0]}&address=${condation.address[0]}&email=${condation.email[0]}">${page}</a></li>
                    </c:if>
                    <c:if test="${pb.currentPage != page}">
                        <li><a href="${pageContext.request.contextPath}/beanPageServlet?currentPage=${page}&rows=5&name=${condation.name[0]}&address=${condation.address[0]}&email=${condation.email[0]}">${page}</a></li>
                    </c:if>
                </c:forEach>
                    <c:if test="${pb.currentPage == pb.totalPage}">
                    <li class="disabled">
                        </c:if>
                        <c:if test="${pb.currentPage < pb.totalPage}">
                    <li>
                </c:if>
                    <a href="${pageContext.request.contextPath}/beanPageServlet?currentPage=${pb.currentPage+1}&rows=5&name=${condation.name[0]}&address=${condation.address[0]}&email=${condation.email[0]}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 24px">共${pb.totalCount}条记录,共${pb.totalPage}页</span>
            </ul>

        </div>
    </div>
</body>
</html>
