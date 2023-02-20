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
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");


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
    <script defer src="/js/main.js"></script>
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
                                onclick="location.href='login.jsp'">
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

<main>
    <%--    <div class="wrap" style="display:none">--%>
    <%--    <div class="wrap">--%>

    <%--        <div class="login-after">--%>
    <%--            <div class="couponpass" style="display: none;">--%>
    <%--                <span>쿠폰패스 이용고객이시군요!!쿠폰패스로 발급된 쿠폰을 확인해보세요~ </span>--%>
    <%--            </div>--%>
    <%--            <div class="user-info">--%>
    <%--                <p class="txt">안녕하세요!</p>--%>

    <%--                <p class="info">--%>
    <%--                    <!----%>
    <%--                        vip 일때만 출력--%>
    <%--                        vip 아닐때는 태그 자체 삭제--%>
    <%--                    -->--%>
    <%--                    <em class="vip">VIP PREMIUM</em>--%>
    <%--                    <em class="name"></em>--%>
    <%--                    <span>회원님</span>--%>
    <%--                </p>--%>

    <%--                <div class="last-date">마지막 접속일 : <em></em></div>--%>

    <%--                <!-- vip, 멤버십 없을때는 미 출력  -->--%>
    <%--                <div class="membership">--%>
    <%--                    <!-- <i class="iconset ico-header-vip"></i>--%>
    <%--                    <i class="iconset ico-header-film"></i>--%>
    <%--                    <i class="iconset ico-header-classic"></i> -->--%>
    <%--                </div>--%>

    <%--                <div class="btn-fixed">--%>
    <%--                    <a href="/mypage" class="button" title="나의  메가박스">나의 메가박스</a>--%>
    <%--                </div>--%>
    <%--            </div>--%>

    <%--            <div class="box">--%>
    <%--                <div class="point">--%>
    <%--                    <p class="tit">Point</p>--%>

    <%--                    <p class="count">--%>
    <%--                        0--%>
    <%--                    </p>--%>

    <%--                    <div class="btn-fixed">--%>
    <%--                        <a href="/mypage/point-list" class="button" title="멤버십 포인트">멤버십 포인트</a>--%>
    <%--                    </div>--%>
    <%--                </div>--%>
    <%--            </div>--%>

    <%--            <div class="box">--%>
    <%--                <div class="coupon">--%>
    <%--                    <p class="tit">쿠폰/관람권 <!-- <i class="iconset ico-header-new"></i> --></p>--%>

    <%--                    <p class="count">--%>
    <%--                        <em title="쿠폰 수" class="cpon">0</em>--%>
    <%--                        <span title="관람권 수" class="movie">0</span>--%>
    <%--                    </p>--%>

    <%--                    <div class="btn-fixed">--%>
    <%--                        <a href="/mypage/discount-coupon" class="button" title="쿠폰">쿠폰</a>--%>
    <%--                        <a href="/mypage/movie-coupon" class="button" title="관람권">관람권</a>--%>
    <%--                    </div>--%>
    <%--                </div>--%>
    <%--            </div>--%>

    <%--            <div class="box">--%>
    <%--                <div class="reserve">--%>
    <%--                    <p class="tit">예매 <!-- <i class="iconset ico-header-new"></i> --></p>--%>

    <%--                    <p class="txt"></p>--%>

    <%--                    <div class="btn-fixed">--%>
    <%--                        <a href="/mypage/bookinglist" class="button" title="예매내역">예매내역 </a>--%>
    <%--                    </div>--%>
    <%--                </div>--%>
    <%--            </div>--%>

    <%--            <div class="box">--%>
    <%--                <div class="buy">--%>
    <%--                    <p class="tit">구매 <!-- <i class="iconset ico-header-new"></i> --></p>--%>

    <%--                    <p class="count">--%>
    <%--                        <em>0</em>--%>
    <%--                        <span>건</span>--%>
    <%--                    </p>--%>
    <%--                </div>--%>

    <%--                <div class="btn-fixed">--%>
    <%--                    <a href="/mypage/bookinglist?tab=02" class="button" title="구매내역">구매내역</a>--%>
    <%--                </div>--%>
    <%--            </div>--%>
    <%--        </div>--%>

    <%--        <!-- 로그인 전 -->--%>
    <%--        <div class="login-before">--%>
    <%--            <p class="txt">--%>
    <%--                로그인 하시면 나의 메가박스를 만날 수 있어요.<br/>--%>
    <%--                영화를 사랑하는 당신을 위한 꼭 맞는 혜택까지 확인해 보세요!--%>
    <%--            </p>--%>

    <%--            <div class="mb50">--%>
    <%--                <a href="#layer_login_common" id="moveLogin" title="로그인" class="button w120px btn-modal-open"--%>
    <%--                   w-data='850' h-data='484'>로그인</a>--%>
    <%--            </div>--%>

    <%--            <a href="/join" class="link" title="혹시 아직 회원이 아니신가요?">혹시 아직 회원이 아니신가요?</a>--%>
    <%--        </div>--%>
    <%--    </div>--%>


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
<%--                    <div class="swiper-pagination"></div>--%>
<%--                    <div class="swiper-prev">--%>
<%--                        <div class="material-icons">arrow_back</div>--%>
<%--                    </div>--%>
<%--                    <div class="swiper-next">--%>
<%--                        <div class="material-icons">arrow_forward</div>--%>
<%--                    </div>--%>
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
