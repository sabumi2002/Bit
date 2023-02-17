<%@ page import="model.UserDTO" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="controller.UserController" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.MovieController" %>
<%@ page import="model.MovieDTO" %><%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-02-17
  Time: 오전 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");


        int id = Integer.parseInt(request.getParameter("id"));


        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        MovieController movieController = new MovieController(connectionMaker);
        UserController userController = new UserController(connectionMaker);

        MovieDTO m = movieController.selectOne(id);

        pageContext.setAttribute("userController", userController);
    %>

    <title>Title</title>
    <!-- CSS 적용 -->
    <link rel="stylesheet" href="/css/movie-detail.css"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"> <!-- 구글 매테리얼 아이콘 -->
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>

</head>

<body>
<header>
    <div class="navbar navbar-dark bg-dark shadow-sm">
        <div class="container">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarHeader"
                    aria-controls="navbarHeader" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <a href="/index.jsp" class="navbar-brand d-flex align-items-center">
                <%--                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="none" stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" aria-hidden="true" class="me-2" viewBox="0 0 24 24"><path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"/><circle cx="12" cy="13" r="4"/></svg>--%>
                <img src="/images/main/git.jpg" alt="imageCinema"/>
                <strong>BeomCinema</strong>
            </a>

            <div class="navbar-loginMenu">
                <c:choose>
                    <c:when test="<%=logIn == null%>">
                        <button class="navbar-login" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapseExample"
                                aria-expanded="false" aria-controls="collapseExample"
                                onclick="location.href='/login.jsp'">
                            <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor"
                                 class="bi bi-person-fill" viewBox="0 0 16 16">
                                <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                            </svg>
                        </button>
                    </c:when>
                    <c:otherwise>
                        <ul class="navbar-logout" style="list-style: none; border: none; padding: 2px; margin: 0;">
                            <li style="margin-right: 10px"><%=logIn.getNickname()%>님</li>
                            <li>
                                <a href="/user/logout_logic.jsp">로그아웃</a>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
    <div class="collapse bg-dark" id="navbarHeader">
        <div class="container">
            <div class="row">
                <ul class="headerMenu">
                    <li class="current">
                        <a href="/movie/movie.jsp">영화 찾기</a>
                    </li>
                    <li class="current">
                        <a href="">극장 찾기</a>
                    </li>
                    <li class="current">
                        <a href="">상영 정보</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</header>
<section class="movie-header">
    <div class="movie-header-bg" style="background-image: url('<%=m.getImg()%>');">
        <div class="cover">
            <div class="inner">

                <div class="inner-col">
                    <p class="title"><%=m.getTitle()%>
                    </p>
                    <div class="info">
                        <div class="score">
                            <p class="tit">실관람 평점</p>
                            <p class="cont">
                                <em>9.7</em>
                            </p>
                        </div>
                        <div class="rate">
                            <p class="tit">예매율</p>
                            <p class="cont">
                                <em>3</em>
                                위 (7.7%)
                            </p>
                        </div>
                    </div>
                </div>
                <div>
                    <div class="poster">
                        <img src="<%=m.getImg()%>" alt="<%=m.getTitle()%>>">
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<section>
    <div class="inner">
        <div class="movie-summary">
            <p>주요정보</p>
            <span>
                <%=m.getContent()%>
            </span>
        </div>
    </div>
</section>
<section class="reply">
    <div class="reply-bg">
        <div class="cover">
            <div class="inner">
                <div class="review-score_box">
                    <p class="tit">평점 · 관람평 작성</p>
                    <div class="star-info">
                        <div class="star_rate type5">

                            <button type="button" class="star starR1"></button>
                            <button type="button" class="star starR2"></button>
                            <button type="button" class="star starR1"></button>
                            <button type="button" class="star starR2"></button>
                            <button type="button" class="star starR1"></button>
                            <button type="button" class="star starR2"></button>
                            <button type="button" class="star starR1"></button>
                            <button type="button" class="star starR2"></button>
                            <button type="button" class="star starR1"></button>
                            <button type="button" class="star starR2"></button>
                        </div>
                        <strong class="score_info">
                            <em>10</em>
                        </strong>
                    </div>
                </div>
                <div class="review_box">

                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>
