<%@ page import="model.UserDTO" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="controller.UserController" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.MovieController" %>
<%@ page import="model.MovieDTO" %>
<%@ page import="controller.ReviewController" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.ReviewDTO" %><%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-02-17
  Time: 오전 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");

        int id = Integer.parseInt(request.getParameter("id"));
        session.setAttribute("currentPage", "/movie/movie-detail.jsp?id=" + id);
//
//        System.out.println(session.getAttribute("movieId"));

        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        MovieController movieController = new MovieController(connectionMaker);
        UserController userController = new UserController(connectionMaker);
        ReviewController reviewController = new ReviewController(connectionMaker);

        ArrayList<ReviewDTO> reviewList = reviewController.selectAll(id);


        MovieDTO m = movieController.selectOne(id);

        pageContext.setAttribute("reviewList", reviewList);
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

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <script src="/js/reply/replyUtil.js"></script>
    <script defer src="/js/movie/movie-detail.js"></script>
</head>

<body>
<%--header_nav --%>
<%@include file="/main/header_nav.jsp" %>

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
                                <em><%=reviewController.movieAvg(m.getId())%>
                                </em>
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
                    <c:if test="${logIn ne null and logIn.rank eq 1}">
                        <div class="movie_util_box">
                            <div class="movie_util">
                                <button class="btn btn-outline-success" onclick="movieUpdate()">수정하기</button>
                                <button class="btn btn-outline-danger" onclick="movieDelete()">삭제하기</button>
                            </div>
                        </div>
                    </c:if>
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
    <div class="reply-bg" style="background-image: url('<%=m.getImg()%>');">
        <div class="cover">
            <div class="inner">
                <div class="review-score_box">
                    <p class="tit">평점 · 관람평 작성</p>
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
                        <input type="hidden" id="reply-score" class="hidden_score" name="score" value="10">

                    </div>
                    <div class="review_box">
                        <textarea id="reply-content" name="content" placeholder="평점 및 영화 관람평을 작성해주세요."
                                  title="관람평 작성"></textarea>
                        <button class="review-button" onclick="writeReply()">관람평 작성</button>
                    </div>
                </div>

                <c:choose>
                    <c:when test="${empty reviewList}">
                        <div>아직 등록된 댓글이 없습니다.</div>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${reviewList}" var="reviewDTO">
                            <div class="review-print_box">
                                <div class="story-area">
                                    <div class="user-prof">
                                        <div class="prof-img">
                                            <img src="" alt="프로필 사진"
                                                 title="프로필 사진">
                                        </div>
                                        <p class="user-id">${userController.selectOne(reviewDTO.writerId).nickname}</p>
                                    </div>
                                </div>
                                <div class="story-box">
                                    <div class="tit">관람평</div>
                                    <div class="story-point">${reviewDTO.rating}</div>
                                    <div class="story-txt">
                                        <span>${reviewDTO.review}</span></div>
                                    <c:if test="${reviewDTO.writerId == logIn.id}">
                                        <div id="reply-util" class="story-utill">
                                            <form action="/reply/update.jsp?id=${reviewDTO.id}" method="post">
                                                <button>수정</button>
                                                <input type="hidden" name="movieId" value="<%=m.getId()%>">
                                            </form>
                                            <form action="/reply/delete.jsp?id=${reviewDTO.id}" method="post">
                                                <button>삭제</button>
                                                <input type="hidden" name="movieId" value="<%=m.getId()%>">
                                            </form>
                                        </div>
                                    </c:if>
                                </div>
                            </div>
                            <div class="date">
                                <span><fmt:formatDate value="${reviewDTO.entryDate}" pattern="yy/MM/dd"/></span>
                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</section>


</body>
</html>
