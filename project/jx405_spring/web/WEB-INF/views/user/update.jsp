<%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-03-15
  Time: 오후 8:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/user/updateLogic" method="post">
    <label>password: </label>
    <input type="text" name="password"><br>
    <label>nickname: </label>
    <input type="text" name="nickname"><br>
    <button type="submit">전송</button>
</form>
</body>
</html>
