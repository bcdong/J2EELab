<%--
  Created by IntelliJ IDEA.
  User: Mr.Zero
  Date: 2016/12/22
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>guest home</title>
</head>
<body>
<h2>Guest Home</h2>
<p>
    You are a guest now, click <a href="/login">login</a> to sign in.
</p>
<p>
    总人数：${applicationScope["userCount"]}，已登录人数：${applicationScope["loginedCount"]}， 游客人数：${applicationScope["userCount"]-applicationScope["loginedCount"]}
</p>
</body>
</html>
