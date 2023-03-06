<%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-02-22
  Time: 오후 2:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>


    <link rel="stylesheet" href="/css/movie/update.css"/>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
    <script defer src="/js/movie/write.js"></script>
    <title>Title</title>
</head>
<body>
<div>
    <div class="inner" style="display: block">
        <form action="/movie/write_logic.jsp" method="post" enctype="multipart/form-data">
            <fieldset style="display: flex">
                <div class="img_box">
                    <img id="previewImg" width="700" alt="이미지 영역"/>
                </div>
                <div class="data_box">
                    <div class="input-group mb-3">
                        <span class="input-group-text">영화제목</span>
                        <input type="text" id="input-title" name="title" class="form-control"
                               aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-default">
                    </div>


                    <div class="input-group">
                        <span class="input-group-text">줄거리</span>
                        <textarea class="form-control" id="input-content" name="content"
                                  aria-label="With textarea"></textarea>
                    </div>


                    <p>등급 : <select class="form-select form-select-lg mb-3" id="input-rank"
                                    aria-label=".form-select-lg example"
                                    name="rank">
                        <option value="all" selected>all</option>
                        <option value="12">12</option>
                        <option value="15">15</option>
                        <option value="19">19</option>
                    </select>


                    <div class="input-group mb-3">
                        <span class="input-group-text">개봉일</span>
                        <input type="text" id="input-release" name="release" class="form-control"
                               aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-default">
                    </div>


                    <div class="input-group mb-3">
                        <span class="input-group-text">상영시간(분)</span>
                        <input type="text" id="input-length" name="length" class="form-control"
                               aria-label="Sizing example input"
                               aria-describedby="inputGroup-sizing-default">
                    </div>


                    <div class="input-group mb-3">
                        <input type="file" id="input-file" class="form-control" name="file">
                        <label class="input-group-text" for="input-file">Upload</label>
                    </div>
                    <div class="util">
                        <button type="button" class="cancel" onclick="location.href='/movie/movie.jsp'">
                            취소
                        </button>
                        <button type="submit" class="check">
                            확인
                        </button>
                    </div>
                </div>
            </fieldset>
        </form>
    </div>


</div>
</body>
</html>
