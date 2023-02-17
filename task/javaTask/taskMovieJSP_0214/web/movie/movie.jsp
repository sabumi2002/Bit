<%@ page import="model.UserDTO" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.UserController" %>
<%@ page import="controller.MovieController" %>
<%@ page import="model.MovieDTO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-02-15
  Time: 오전 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>

    <!-- CSS 적용 -->
    <link rel="stylesheet" href="/css/movie.css"/>
    <!-- 폰트 적용 -->
    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&display=swap" rel="stylesheet">
    <title>영화 찾기</title>
</head>

<body>
<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom navbar-dark bg-dark shadow-sm">
    <a href="/" id="logo" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-decoration-none">
        <img src="/images/main/git.jpg" alt="imageCinema"/>
        <strong>BeomCinema</strong>
    </a>

    <ul id="nav-menu" class="nav col-12 col-md-auto mb-2 justify-content-center mb-md-0">
        <li><a href="#" class="nav-link px-2">Home</a></li>
        <li><a href="#" class="nav-link px-2">Features</a></li>
        <li><a href="#" class="nav-link px-2">Pricing</a></li>
        <li><a href="#" class="nav-link px-2">FAQs</a></li>
        <li><a href="#" class="nav-link px-2">About</a></li>
    </ul>

    <div class="col-md-3 text-end">
        <button type="button" class="btn btn-outline-primary me-2">Login</button>
        <button type="button" class="btn btn-primary">Sign-up</button>
    </div>
</header>

<div id="contents">
    <div class="inner-wrap">
        <%
            //            UserDTO logIn = (UserDTO) session.getAttribute("logIn");


            int pageNo;
            try {
                String pageStr = request.getParameter("pageNo");
                pageNo = Integer.parseInt(pageStr);
            } catch (Exception e) {
                pageNo = 1;
            }


            ConnectionMaker connectionMaker = new MySqlConnectionMaker();
            MovieController movieController = new MovieController(connectionMaker);
            UserController userController = new UserController(connectionMaker);

            ArrayList<MovieDTO> list = movieController.selectAll(pageNo);

            int totalPage = movieController.countTotalPage();

            int startNum;
            int endNum;

            if (pageNo < 3) {
                startNum = 1;
                endNum = 5;
            } else if (pageNo > totalPage - 3) {
                startNum = totalPage - 4;
                endNum = totalPage;
            } else if (totalPage <= 5) {
                startNum = 1;
                endNum = totalPage;
            } else {
                startNum = pageNo - 2;
                endNum = pageNo + 2;
            }

            pageContext.setAttribute("list", list);
            pageContext.setAttribute("userController", userController);
            pageContext.setAttribute("currentPage", pageNo);
            pageContext.setAttribute("startPage", startNum);
            pageContext.setAttribute("endPage", endNum);
            pageContext.setAttribute("totalPage", totalPage);

        %>


        <div class="movie-list">
            <c:choose>
            <c:when test="${list.isEmpty()}">
                <div class="row">
                    <div class="col-6">
                        <span>아직 등록된 글이 존재하지 않습니다.</span>
                    </div>
                </div>
            </c:when>

            <c:otherwise>
            <ol class="list" id="movieList">
                <c:forEach var="m" items="${list}">
                    <li tabindex="0" class="no-img">
                        <a href="/movie/movie-detail.jsp?id=${m.id}" class="movie-link">
                            <div class="movie-list-info">
                                <p class="rank" style="">
                                    <span class="ir">위</span>
                                </p>
                                <img
                                        src="${m.img}"
                                        alt="${m.title}" class="poster lozad" onerror="noImg(this)">
                            </div>
                            <div class="tit-area">
                                <p class="movie-grade age-${m.rank}">,</p>
                                <p title="${m.title}" class="tit">${m.title}</p>
                            </div>
                        </a>
                        <div class="rate-date">
                            <span class="rate">예매율 52.1%</span>
                            <span class="date">개봉일 ${m.release}</span></div>
                        <div class="btn-util">
                            <button type="button" class="button btn-like" data-no="22088100"><i title="보고싶어 안함"
                                                                                                class="iconset ico-heart-toggle-gray intrstType"></i>
                                <span>1.7k</span></button>
                            <div class="case movieStat4" style="">
                                <a href="#" class="button purple bokdBtn"
                                   data-no="22103500"
                                   title="영화 예매하기">예매</a>
                            </div>
                        </div>
                    </li>
                </c:forEach>

            </ol>
            <table class="paginationList">
                <tr>
                    <td colspan="5" class="">
                        <ul class="pagination m-auto">
                            <li class="page-item">
                                <a href="/movie/movie.jsp?pageNo=${1}" class="page-link">
                                    <span>&laquo;</span>
                                </a>
                            </li>
                            <c:forEach begin="${startPage}" end="${endPage}" var="i">
                                <c:choose>
                                    <c:when test="${currentPage eq i}">
                                        <li class="page-item active">
                                            <a href="/movie/movie.jsp?pageNo=${i}" class="page-link">
                                                <span>${i}</span>
                                            </a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item">
                                            <a href="/movie/movie.jsp?pageNo=${i}" class="page-link">
                                                <span>${i}</span>
                                            </a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                            <li class="page-item">
                                <a href="/movie/movie.jsp?pageNo=${totalPage}" class="page-link">
                                    <span>&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </td>
                </tr>
            </table>
        </div>
        </c:otherwise>
        </c:choose>

    </div>
</div>
</div>
</body>
</html>