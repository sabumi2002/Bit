<%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-03-06
  Time: 오후 5:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>


    <link rel="stylesheet" href="/css/schedule/write.css"/>
    <script defer src="/js/schedule/write.js"></script>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://code.jquery.com/jquery-3.6.3.min.js"></script>


    <title>상영정보 추가 page</title>
</head>
<%@include file="/main/header_nav.jsp" %>
<body>
<div>
    <div class="inner">
        <div class="img_box">
            <img id="previewImg" width="700" alt="이미지 영역"/>
        </div>
        <div class="data_box">
            <div class="mb-3" style="display: flex; width: 100%">
                <span class="input-group-text">관 선택</span>
                <select class="form-select form-select-lg mb-0" id="input-room"
                        aria-label=".form-select-lg example"
                        name="room" style="display: block; width: 100%">
                    <option value="all" selected>all</option>
                    <option value="12">12</option>
                    <option value="15">15</option>
                    <option value="19">19</option>
                </select>


            </div>

            <div class="mb-3" style="display: flex; width: 100%">
                <span class="input-group-text">영화 선택</span>
                <div style="width: 100%;">
                    <select class="form-select form-select-lg mb-0" id="input-rank"
                            aria-label=".form-select-lg example"
                            name="rank" style="display: block; width: 100%">
                        <option value="all" selected>all</option>
                        <option value="12">12</option>
                        <option value="15">15</option>
                        <option value="19">19</option>
                    </select>
                    <select class="form-select form-select-lg mb-0" id="input-movie"
                            aria-label=".form-select-lg example"
                            name="movie" style="display: block; width: 100%">
                        <option value="all" selected>all</option>
                        <option value="12">12</option>
                        <option value="15">15</option>
                        <option value="19">19</option>
                    </select>
                </div>


            </div>


            <div class="input-group mb-3">
                <span class="input-group-text">날짜</span>
                <input type="text" id="input-location" name="location" class="form-control"
                       aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-default">
            </div>


            <div class="input-group mb-3">
                <span class="input-group-text">시간</span>
                <input type="text" id="input-phoneNumber" name="phoneNumber" class="form-control"
                       aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-default">
            </div>

            <div class="util">
                <button type="button" class="cancel" onclick="location.href='/cinema/cinema.jsp'">
                    취소
                </button>
                <button type="submit" class="check" onclick="fileUpload()">
                    확인
                </button>
            </div>
        </div>

    </div>
</div>
</body>
</html>