<%@ page import="model.UserDTO" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.ReviewController" %>
<%@ page import="model.ReviewDTO" %><%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-02-20
  Time: 오후 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    UserDTO logIn = (UserDTO) session.getAttribute("logIn");
    if (logIn == null) {
        response.sendRedirect("/index.jsp");
    }

    int id = Integer.parseInt(request.getParameter("id"));
    int movieId = Integer.parseInt(request.getParameter("movieId"));

    ConnectionMaker connectionMaker = new MySqlConnectionMaker();
    ReviewController reviewController = new ReviewController(connectionMaker);

    ReviewDTO r = reviewController.selectOne(id);
    if (r.getWriterId() == logIn.getId()) {
        reviewController.delete(id);
    }

    response.sendRedirect("/movie/movie-detail.jsp?id="+movieId);
%>

</body>
</html>
