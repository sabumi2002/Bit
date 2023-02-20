<%@ page import="model.UserDTO" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.ReviewController" %>
<%@ page import="model.ReviewDTO" %><%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-02-20
  Time: 오후 5:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%
        request.setCharacterEncoding("UTF-8");
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        int movieId = Integer.parseInt(request.getParameter("movieId"));
        int id = Integer.parseInt(request.getParameter("id"));
        int rating = Integer.parseInt(request.getParameter("score"));
        String review = request.getParameter("comment");

        if (logIn == null) {
            response.sendRedirect("/movie/movie.jsp?id=" + movieId);
        }

        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        ReviewController reviewController = new ReviewController(connectionMaker);

        ReviewDTO r = reviewController.selectOne(id);

        pageContext.setAttribute("r", r);
        pageContext.setAttribute("logIn", logIn);
    %>

    <title>Title</title>
</head>
<body>
    <%
        if (r.getWriterId() != logIn.getId()) {
            response.sendRedirect("/movie/movie-detail.jsp?id="+movieId);
        }

        r.setRating(rating);
        r.setReview(review);

        reviewController.update(r);

        response.sendRedirect("/movie/movie-detail.jsp?id="+movieId);
    %>
</body>
</html>
