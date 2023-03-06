<%@ page import="model.UserDTO" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="controller.UserController" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.MovieController" %>
<%@ page import="model.MovieDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%
        session.setAttribute("currentPage", "/index.jsp");

        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        UserController userController = new UserController(connectionMaker);
        MovieController movieController = new MovieController(connectionMaker);

        ArrayList<MovieDTO> movieList = movieController.selectAll(1);


        pageContext.setAttribute("userController", userController);
        pageContext.setAttribute("movieList", movieList);

    %>
    <title>index</title>
    <!-- SWIPER -->
    <link rel="stylesheet" href="https://unpkg.com/swiper@7/swiper-bundle.min.css"/>
    <script src="https://unpkg.com/swiper@7/swiper-bundle.min.js"></script>
    <!-- CSS 적용 -->
    <link rel="stylesheet" href="/css/main.css"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

    <link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic:wght@400;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons"> <!-- 구글 매테리얼 아이콘 -->

    <link href="/assets/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/album/">


    <!-- defer속성을 이용해서 js구조가 모두 해석되면 main.js가 정상적으로 동작하도록 함  -->
    <script defer src="/js/main/index.js"></script>
</head>
<body>
<jsp:include page="/main/header_nav.jsp"></jsp:include>
<main>
    <section class="notice">
        <c:choose>
            <c:when test="${empty movieList}">
                <div>등록된 영화가 없습니다.</div>
            </c:when>
            <c:otherwise>

                <div class="promotion">
                    <div class="swiper-container">
                        <!-- swiper라이브러리를 사용할려면 필수적으로 swiper-wrapper 클래스를 생성해줘야함 -->
                        <div class="swiper-wrapper">
                            <c:forEach items="${movieList}" var="movieDTO">
                                <div class="swiper-slide">
                                    <a href="/movie/movie-detail.jsp?id=${movieDTO.id}">
                                        <img src="${movieDTO.img}" alt="무비시네마와 함께 즐겁고 활기차게 시작하세요!"/>
                                    </a>
                                </div>

                            </c:forEach>
                        </div>
                    </div>
                    <div class="swiper-util">
                        <div class="swiper-prev">
                            <div class="material-icons">arrow_back</div>
                        </div>

                        <div class="swiper-pagination"></div>

                        <div class="swiper-next">
                            <div class="material-icons">arrow_forward</div>
                        </div>
                    </div>

                </div>
            </c:otherwise>
        </c:choose>


    </section>


</main>

<footer class="text-muted py-5">
    <div class="container">
        <p class="float-end mb-1">
            <a href="#">Back to top</a>
        </p>
        <p class="mb-1">Album example is &copy; Bootstrap, but please download and customize it for yourself!</p>
        <p class="mb-0">New to Bootstrap? <a href="/">Visit the homepage</a> or read our <a
                href="../getting-started/introduction/">getting started guide</a>.</p>
    </div>
</footer>


<script src="../assets/dist/js/bootstrap.bundle.min.js"></script>


</body>
</html>
