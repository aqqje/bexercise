<%@ page import="cn.aqqje.domain.User" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <title>el&jstl</title>
  </head>
  <body>
    <%
      List<User> users = new ArrayList<User>();
      User u1 = new User(1, "aqqje1", "male", 28, new Date());
      User u2 = new User(2, "aqqje2", "female", 14, new Date());
      User u3 = new User(3, "aqqje3", "female", 16, new Date());
      User u4 = new User(4, "aqqje4", "male", 14, new Date());
      User u5 = new User(5, "aqqje5", "male", 12, new Date());
      User u6 = new User(6, "aqqje6", "female", 11, new Date());
      users.add(u1); users.add(u2); users.add(u3);
      users.add(u4); users.add(u5); users.add(u6);
      session.setAttribute("users", users);
    %>
  <table border="1px solid red">
    <thead>
    <td>ID</td>
    <td>用户名</td>
    <td>性别</td>
    <td>年龄</td>
    <td>生日</td>
    </thead>
    <c:forEach items="${users}" varStatus="vs" var="user">
      <c:if test="${vs.index %2 == 1}">
        <tr bgcolor="red">
          <td>${vs.index}</td>
          <td>${user.name}</td>
          <td>${user.gender}</td>
          <td>${user.age}</td>
          <td>${user.brithStr}</td>
        </tr>
      </c:if>
      <c:if test="${vs.index %2 == 0}">
        <tr bgcolor="blue">
          <td>${vs.index}</td>
          <td>${user.name}</td>
          <td>${user.gender}</td>
          <td>${user.age}</td>
          <td>${user.brithStr}</td>
        </tr>
      </c:if>
    </c:forEach>
  </table>

  </body>
</html>
