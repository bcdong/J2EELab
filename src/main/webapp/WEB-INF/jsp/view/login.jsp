<%--
  Created by IntelliJ IDEA.
  User: Mr.Zero
  Date: 2016/12/20
  Time: 20:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<p style="color: red;">
<c:if test="${login_failed}">
    对不起，用户名或密码错误！
    <br />
</c:if>
</p>
<form method="post" action="<c:url value="/login" />">
    姓名:
    <input type="text" name="username" value="${last_name}" placeholder="username"/>
    <br />
    密码:
    <input type="password" name="password" placeholder="password" />
    <br />
    <input type="submit" value="登录">
</form>
</body>
</html>
