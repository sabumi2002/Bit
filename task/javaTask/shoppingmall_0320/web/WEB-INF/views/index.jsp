<%--
  Created by IntelliJ IDEA.
  User: sabum
  Date: 2023-03-20
  Time: 오후 1:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
            crossorigin="anonymous"></script>
    <title>인덱스</title>
</head>
<body>
<div class="container">
    <div class="row h-100 align-items-center">
        <div class="row">
            <div class="col-10 align-items-center">
                <form method="post" action="/user/auth">
                    <label for="input-username">아이디</label>
                    <input type="text" name="username" class="form-control" id="input-username">
                    <label for="input-password">비밀번호</label>
                    <input type="password" name="password" class="form-control" id="input-password">
                    <button type="submit" class="btn btn-outline-light">로그인</button>
                    <a class="btn btn-outline-info" href="/user/register">회원가입하기</a>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
