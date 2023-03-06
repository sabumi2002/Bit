<%@ page import="model.UserDTO" %>
<%@ page import="controller.CinemaController" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.CinemaDTO" %>
<%@ page import="controller.ScheduleController" %>
<%@ page import="model.ScheduleDTO" %>
<%@ page import="model.MovieDTO" %>
<%@ page import="controller.MovieController" %><%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-02-21
  Time: 오전 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%
        request.setCharacterEncoding("UTF-8");
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        session.setAttribute("currentPage", "/cinema/cinema.jsp");  // 세션에 현재 페이지 저장

        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        CinemaController cinemaController = new CinemaController(connectionMaker);
        ScheduleController scheduleController = new ScheduleController(connectionMaker);
        MovieController movieController = new MovieController(connectionMaker);

        ArrayList<CinemaDTO> list = cinemaController.selectAll();

        if (!list.isEmpty()) {
            CinemaDTO cinemaDTO = list.get(0);
            pageContext.setAttribute("cinemaDTO", cinemaDTO);
        }


        pageContext.setAttribute("logIn", logIn);
        pageContext.setAttribute("list", list);
    %>
    <!-- CSS 적용 -->
    <link rel="stylesheet" href="/css/cinema/cinema.css"/>
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
    <script defer src="/js/cinema/cinema.js"></script>
    <title>Title</title>
</head>

<body>
<%--header menu--%>
<jsp:include page="/main/header_nav.jsp"></jsp:include>
<div id="hanzi">
    <section class="cinemas">
        <div class="cinema-bg">
            <div class="inner">
                <div class="tit">
                    <p>THEATER</p>
                </div>
                <ul class="items">
                    <c:choose>
                        <c:when test="${empty list}">
                            <li style="margin: 0 auto">아직 등록된 댓글이 없습니다.</li>
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="c" items="${list}">
                                <li>
                                    <a href="/cinema/cinema-detail.jsp?id=${c.id}">${c.name}</a>
                                </li>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
        <%--    극장 추가 수정 삭제--%>
        <c:if test="${logIn ne null and logIn.rank eq 1}">
            <div class="cinema-admin bg-dark text-center">
                <div class="cinema-util">
                    <button class="btn btn-outline-info" onclick="location.href='/cinema/write.jsp'">극장 추가하기</button>
                    <c:if test="${!empty list}">
                        <button class="btn btn-outline-success"
                                onclick="location.href='/cinema/update.jsp?id=${cinemaDTO.id}'">
                            극장 수정하기
                        </button>
                        <button class="btn btn-outline-danger"
                                onclick="cinemaDelete(${cinemaDTO.id})">극장 삭제하기
                        </button>
                    </c:if>
                </div>
            </div>
        </c:if>

    </section>
    <section class="cinema">
        <c:choose>
            <c:when test="${empty list}">
                <div style="margin: 0 auto">등록된 극장이 없습니다.</div>
            </c:when>
            <c:otherwise>
                <%

                    CinemaDTO cinemaDTO = list.get(0);

                    // 극장이 갖고있는 영화 종류
                    ArrayList<Integer> hasMovieList = scheduleController.cinemaMovieList(cinemaDTO.getId());
                    // 극장이 갖고있는 모든영화 스케쥴
                    ArrayList<ScheduleDTO> detailList = scheduleController.selectAll(cinemaDTO.getId());

                    int moviePlace = 1;

                    pageContext.setAttribute("moviePlace", moviePlace);
                    pageContext.setAttribute("detailList", detailList);
                    pageContext.setAttribute("hasMovieList", hasMovieList);
                    pageContext.setAttribute("c", cinemaDTO);
                %>
                <%--        <c:set var="c" value="${list.get(0)}"/>--%>
                <div class="container">
                    <div class="inner">
                        <div class="tit">
                            <p>${c.name}</p>
                        </div>
                        <div class="img_box ">
                            <img src="/images/cinema/cinema.jpg">
                            <div class="cinema-info-cover">
                                <div class="cinema-info">
                                    <span>${c.location}</span>
                                    <span>${c.phoneNumber}</span>
                                </div>
                            </div>
                        </div>
                            <%--                    상영정보 추가 수정 삭제--%>
                        <c:if test="${logIn ne null and logIn.rank eq 1}">
                            <div class="img_box bg-dark text-center">
                                <div class="cinema-util">
                                    <button class="btn btn-outline-info" onclick="location.href='/schedule/write.jsp?id=${c.id}'">
                                        상영정보 추가하기
                                    </button>
                                        <%--                                <button class="btn btn-outline-success">수정하기</button>--%>
                                        <%--                                <button class="btn btn-outline-danger">삭제하기</button>--%>
                                </div>
                            </div>
                        </c:if>
                        <div class="detail">
                            <c:forEach var="hasMovie" items="${hasMovieList}">
                                <%

                                    MovieDTO movieDTO = movieController.selectOne(Integer.parseInt(String.valueOf(pageContext.getAttribute("hasMovie"))));
                                    ArrayList<ScheduleDTO> movieList = scheduleController.selectAll(cinemaDTO.getId(), Integer.parseInt(String.valueOf(pageContext.getAttribute("hasMovie"))));
                                    pageContext.setAttribute("movieList", movieList);   // 극장, 영화번호에 맞는 모든 상영정보
                                    pageContext.setAttribute("movieDTO", movieDTO); // hasMovie
                                %>
                                <div class="movie_box">
                                    <div class="tit_box">
                                        <p class="tit">${movieDTO.title}</p>
                                        <p>/ ${movieDTO.length}분</p>
                                        <p>/ ${movieDTO.release} 개봉</p>
                                    </div>
                                    <div class="content">
                                        <c:forEach var="m" items="${movieList}">
                                            <div class="movie_info">
                                                <p>${m.room}관</p>
                                                <div class="items"
                                                     onclick="location.href='/schedule/schedule.jsp?id=${m.id}'">
                                                    <p>${m.runningTime}</p>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </c:forEach>

                        </div>
                    </div>
                </div>


            </c:otherwise>
        </c:choose>
    </section>
</div>
</body>
</html>
























