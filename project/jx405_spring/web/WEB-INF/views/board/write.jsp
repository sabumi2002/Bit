<%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-03-16
  Time: 오후 2:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/board/write" method="post">
    제목: <input type="text" name="title">
    내용: <input type="text" name="content">
    <br>
    <br>
    <button type="submit">작성하기</button>
</form>
</body>
</html>
