<%--
  Created by IntelliJ IDEA.
  User: Mr.Zero
  Date: 2016/12/22
  Time: 11:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
</head>
<body>
<div>
    <h2 style="display: inline-block">Hello ${username}!</h2>
    <a href="/logout">退出</a>
</div>
<p>
    你选择的课程有：
    <ul>
    <c:forEach items="${courseList}" var="item">
        <li>${item}</li>
    </c:forEach>
    </ul>
</p>
<p style="color: red;">
    <c:if test="${fn:length(absentExamList) > 0}">
        警告：您有${fn:length(absentExamList)}门考试缺考：
        <ul style="color: red;">
        <c:forEach items="${absentExamList}" var="exam">
            <li>${exam}</li>
        </c:forEach>
        </ul>
    </c:if>
</p>
<p>
    总人数：${applicationScope["userCount"]}，已登录人数：${applicationScope["loginedCount"]}， 游客人数：${applicationScope["userCount"]-applicationScope["loginedCount"]}
</p>
</body>
</html>
