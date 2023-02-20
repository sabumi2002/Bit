<%@ page import="model.ReviewDTO" %>
<%@ page import="model.UserDTO" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.ReviewController" %><%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-02-20
  Time: 오후 1:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
  request.setCharacterEncoding("UTF-8");
  UserDTO logIn = (UserDTO) session.getAttribute("logIn");
  if (logIn == null) {
    response.sendRedirect("/index.jsp");
  }
  int movieId = Integer.parseInt(request.getParameter("movieId"));
  String content = request.getParameter("content");
  int rating = Integer.parseInt(request.getParameter("score"));


  ReviewDTO reviewDTO = new ReviewDTO();
  reviewDTO.setMovieId(movieId);
  reviewDTO.setWriterId(logIn.getId());
  reviewDTO.setReview(content);
  reviewDTO.setRating(rating);

  ConnectionMaker connectionMaker = new MySqlConnectionMaker();
  ReviewController reviewController = new ReviewController(connectionMaker);

  reviewController.insert(reviewDTO);


  response.sendRedirect("/movie/movie-detail.jsp?id=" + movieId);

%>
</body>
</html>
