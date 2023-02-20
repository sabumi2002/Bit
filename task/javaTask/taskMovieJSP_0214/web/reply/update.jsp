<%@ page import="model.UserDTO" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.ReviewController" %>
<%@ page import="model.ReviewDTO" %>
<%@ page import="controller.UserController" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-02-20
  Time: 오후 4:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%--    movieid(input), 로그인(세션), reviewid(쿼리), --%>
    <%
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        int movieId = Integer.parseInt(request.getParameter("movieId"));
        int id = Integer.parseInt(request.getParameter("id"));
        if (logIn == null) {
            response.sendRedirect("/movie/movie.jsp?id=" + movieId);
        }

        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        ReviewController reviewController = new ReviewController(connectionMaker);

        ReviewDTO r = reviewController.selectOne(id);

        pageContext.setAttribute("r", r);
        pageContext.setAttribute("logIn", logIn);
    %>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>

    <!-- CSS 적용 -->
    <link rel="stylesheet" href="/css/reply/update.css"/>
    <!-- 폰트 적용 -->
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&display=swap" rel="stylesheet">
    <script defer src="/js/reply/update.js"></script>

    <title>Title</title>
</head>
<body>
<header>
    <div class="inner">
        <div class="tit">댓글수정</div>
    </div>
</header>

<section class="update">
    <div class="inner">
        <form action="/reply/update_logic.jsp?id=<%=r.getId()%>" method="post">
            <div class="prof-info">
                <p class="tit">작성자: ${logIn.nickname}</p>
                <div class="review-score_box">

                    <div class="star-info">
                        <div class="star_rate">
                            <button type="button" name="rating" value="1" class="star starR1"
                                    onclick="rating_btn(value)"></button>
                            <button type="button" name="rating" value="2" class="star starR2"
                                    onclick="rating_btn(value)"></button>
                            <button type="button" name="rating" value="3" class="star starR1"
                                    onclick="rating_btn(value)"></button>
                            <button type="button" name="rating" value="4" class="star starR2"
                                    onclick="rating_btn(value)"></button>
                            <button type="button" name="rating" value="5" class="star starR1"
                                    onclick="rating_btn(value)"></button>
                            <button type="button" name="rating" value="6" class="star starR2"
                                    onclick="rating_btn(value)"></button>
                            <button type="button" name="rating" value="7" class="star starR1"
                                    onclick="rating_btn(value)"></button>
                            <button type="button" name="rating" value="8" class="star starR2"
                                    onclick="rating_btn(value)"></button>
                            <button type="button" name="rating" value="9" class="star starR1"
                                    onclick="rating_btn(value)"></button>
                            <button type="button" name="rating" value="10" class="star starR2"
                                    onclick="rating_btn(value)"></button>
                        </div>
                        <strong class="score_info">
                            <em>10</em>
                        </strong>
                        <input type="hidden" class="hidden_score" name="score" value="10">
                        <input type="hidden" name="movieId" value="<%=movieId%>">
                    </div>
                </div>
            </div>
            <div class="container">
        <textarea id="comment" name="comment" cols="45" rows="9" placeholder="댓글을 작성해주세요"></textarea>
            </div>
            <div class="util">
                <button type="button" class="cancel" onclick="location.href='/movie/movie-detail.jsp?id=<%=movieId%>'">
                    취소
                </button>
                <button type="submit" class="check">
                    확인
                </button>
            </div>
        </form>
    </div>
</section>


</body>
</html>
