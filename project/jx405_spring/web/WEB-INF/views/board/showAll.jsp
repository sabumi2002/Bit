<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Navbar</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor03"
                    aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarColor03">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Home
                            <span class="visually-hidden">(current)</span>
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Features</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Pricing</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">About</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                           aria-haspopup="true" aria-expanded="false">Dropdown</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#">Action</a>
                            <a class="dropdown-item" href="#">Another action</a>
                            <a class="dropdown-item" href="#">Something else here</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Separated link</a>
                        </div>
                    </li>
                </ul>
                <form class="d-flex" action="/board/search/1" method="get">
                    <input class="form-control me-sm-2" type="search" placeholder="Search" name="keyword">
                    <button class="btn btn-secondary my-2 my-sm-0" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
    <button class="btn btn-outline-primary" onclick="location.href='/user/update'">유저정보수정</button>
    <button class="btn btn-outline-danger" onclick="location.href='/user/delete'">유저탈퇴</button>
    ${message}
    <br><br>
    <div class="row h-100 align-items-center">
        <div class="col-10">
            <div class="row">
                <div class="col">
                    <table class="table table-hover table-striped">
                        <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                            <th>수정일</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="item">
                            <tr onclick="location.href='/board/showOne/${item.id}'">
                                <td>${item.id}</td>
                                <td>${item.title}</td>
                                <td>${item.writerNickname}</td>
                                <td>${item.entryDate}</td>
                                <td>${item.modifyDate}</td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td colspan="5" class="">
                                <ul class="pagination">
                                    <li class="page-item">
                                        <a class="page-link" href="${pagingAddr}/1?keyword=${keyword}">&laquo</a>
                                    </li>
                                    <c:forEach var="i" begin="${paging.start}" end="${paging.end}">
                                        <c:choose>
                                            <c:when test="${i eq current}">
                                                <li class="page-item active disabled">
                                                    <a class="page-link"
                                                       href="${pagingAddr}/${i}?keyword=${keyword}">${i}</a>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a class="page-link"
                                                       href="${pagingAddr}/${i}?keyword=${keyword}">${i}</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="${pagingAddr}/${paging.totalPage}?keyword=${keyword}">&raquo</a>
                                    </li>
                                </ul>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<br>
<a href="/board/write">글작성하기</a>
</body>
</html>
