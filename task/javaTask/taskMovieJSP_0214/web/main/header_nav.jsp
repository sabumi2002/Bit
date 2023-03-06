<%--<%@ page import="model.UserDTO" %>&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: Sabeom--%>
<%--  Date: 2023-02-21--%>
<%--  Time: 오전 11:09--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.UserDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <script src="/js/main/header_nav.js"></script>
    <link rel="stylesheet" href="/css/main.css"/>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <style>
        header {
            position: fixed;
            width: 100%;
            z-index: 999999;
        }

        .navbar {
            position: absolute;
            width: 100%;
        }

        #navbarHeader {
            position: absolute;
            width: 100%;
            top: 50px;
        }

        #nav {
            height: 50px;
        }

        #login_box {
            position: absolute;
            top: 50px;
        }

        #login_box .login_util {
            right: 0 !important;
        }

        #login_box .login_util .btn {
            display: block;
            right: 0 !important;
        }
    </style>
</head>

<%--<%--%>
<%--    request.setCharacterEncoding("UTF-8");--%>
<%--    pageContext.setAttribute("logIn", (UserDTO) session.getAttribute("logIn"));--%>
<%--%>--%>
<body onload="initNav()">

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
                <%--                <c:choose>--%>
                <%--                    <c:when test="${logIn eq null}">--%>
                <button class="navbar-login" data-bs-toggle="collapse"
                        data-bs-target="#collapseExample"
                        aria-expanded="false" aria-controls="collapseExample"
                        onclick="location.href='/user/login.jsp'">
                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor"
                         class="bi bi-person-fill" viewBox="0 0 16 16">
                        <path d="M3 14s-1 0-1-1 1-4 6-4 6 3 6 4-1 1-1 1H3Zm5-6a3 3 0 1 0 0-6 3 3 0 0 0 0 6Z"/>
                    </svg>
                </button>
                <%--                    </c:when>--%>
                <%--                    <c:otherwise>--%>
                <button class="navbar-logout" data-bs-toggle="collapse" data-bs-target="#login_box"
                        aria-controls="navbarHeader" aria-expanded="false">
                </button>
                <%--                    </c:otherwise>--%>
                <%--                </c:choose>--%>
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
                        <a href="/cinema/cinema.jsp">극장 찾기</a>
                    </li>
                    <li class="current">
                        <a href="">상영 정보</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <%--    <c:if test="${logIn ne null}">--%>
    <div id="login_box" style="right: 7%">
        <div class="login_util bg-dark">
            <button class="btn btn-outline-success" onclick="location.href='/user/logout_logic.jsp'">로그아웃</button>
            <button class="btn btn-outline-success">회원수정</button>
            <button class="btn btn-outline-danger">회원탈퇴</button>
        </div>
    </div>
    <%--    </c:if>--%>

</header>
<div id="nav"></div>
</body>
<%--</body>--%>
<%--</html>--%>
