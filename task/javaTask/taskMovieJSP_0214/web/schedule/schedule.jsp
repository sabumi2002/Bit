<%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-03-06
  Time: 오전 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <!-- CSS 적용 -->
    <link rel="stylesheet" href="/css/schedule/schedule.css"/>
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>

    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>


    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>
    <script defer src="/js/schedule/schedule.js"></script>
    <title>Schedule</title>
</head>

<%--header menu--%>
<jsp:include page="/main/header_nav.jsp"></jsp:include>
<body>
<div id="hanzi">
    <div class="inner">
        <div class="img_box">
            <img id="movieImg" width="700" alt="이미지 영역"/>
        </div>
        <div class="data_box">
            <div class="movieBox">
                <span id="movieRank" class="movie-grade">,</span>
                <span id="movieTit" class="tit"></span>
            </div>
            <div class="cinemaBox">
                <div style="display: flex;">
                    <p class="col-2">극장 : </p>
                    <p id="cinemaTit"></p>
                </div>

                <div style="display: flex;">
                    <p class="col-2">위치 : </p>
                    <p id="cinemaLoc"></p>
                </div>

                <div style="display: flex;">
                    <p class="col-2">번호 : </p>
                    <p id="cinemaPhone"></p>
                </div>

            </div>
            <div class="scheduleBox">
                <div style="display: flex;">
                    <p class="col-2">날짜 : </p>
                    <p id="scheduleDate"></p>
                </div>
                <div style="display: flex;">
                    <p class="col-2">시간 : </p>
                    <p id="startTime"></p>
                    <p>~</p>
                    <p id="endTime"></p>
                </div>
            </div>
        </div>

    </div>
</div>

</body>
</html>
