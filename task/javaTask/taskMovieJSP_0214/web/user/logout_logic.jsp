<%@ page import="model.UserDTO" %><%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-02-14
  Time: 오후 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    session.removeAttribute("logIn");
    response.sendRedirect("/index.jsp");
%>
</body>
</html>
