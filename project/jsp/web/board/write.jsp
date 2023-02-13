<%@ page import="model.UserDTO" %><%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-02-10
  Time: 오후 3:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if (logIn == null) {
            response.sendRedirect("/index.jsp");
        }

    %>
    <title>글 작성하기</title>
    <!-- CSS only -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <!-- JavaScript Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
            crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid">
    <div class="row align-items-center vh-100">
        <div class="row">
            <div class="col-10">
                <form action="/board/write_logic.jsp" method="post">
                    <table class="table table-striped table-dark">
                        <tr>
                            <th>제목</th>
                            <td><input type="text" name="title" class="form-control">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                            <textarea name="content" class="form-control">

                            </textarea>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="text-center">
                                <button class="btn btn-outline-success">작성하기</button>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
