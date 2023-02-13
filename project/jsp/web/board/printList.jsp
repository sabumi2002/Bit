<%@ page import="model.UserDTO" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.BoardController" %>
<%@ page import="java.security.BasicPermission" %>
<%@ page import="model.BoardDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="controller.UserController" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시판</title>
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
    <div class="row vh-100 align-items-center">
        <%
            UserDTO logIn = (UserDTO) session.getAttribute("logIn");

            if (logIn == null) {
                response.sendRedirect("/index.jsp");
            }

            ConnectionMaker connectionMaker = new MySqlConnectionMaker();
            BoardController boardController = new BoardController(connectionMaker);
            UserController userController = new UserController(connectionMaker);

            ArrayList<BoardDTO> list = boardController.selectAll();
            pageContext.setAttribute("List", list);
            pageContext.setAttribute("userController", userController);
        %>
        <c:choose>
            <c:when test="${list.isEmpty()}">
                <div class="row">
                    <div class="col-6">
                        <span>아직 등록된 글이 존재하지 않습니다.</span>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <c:set var="list" value="<%=list%>"/>

                <div class="row justify-content-center">
                    <div class="col-10">
                        <table class="table table-primary table-hover">
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
                            <c:forEach var="b" items="${list}">
                                <tr class="table-danger" onclick="location.href='printOne.jsp?id=${b.id}'">
                                    <td>
                                            ${b.id}
                                    </td>
                                    <td>
                                            ${b.title}
                                    </td>
                                    <td>
                                            ${userController.selectOne(b.writerId).nickname}
                                    </td>
                                    <td>
                                            ${b.entryDate}
                                    </td>
                                    <td>
                                            ${b.modifyDate}
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>

                    </div>
                </div>


            </c:otherwise>
        </c:choose>
        <div class="row">
            <div class="col-12 text-end">
                <span class="btn btn-outline-info" onclick="location.href='write.jsp'">글 작성하기</span>
            </div>
        </div>

        if (list.isEmpty()) {
        %>


    </div>
</div>
</body>
</html>


<%--<html>--%>
<%--<head>--%>
<%--    <title>게시판</title>--%>
<%--    <!-- CSS only -->--%>
<%--    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet"--%>
<%--          integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">--%>
<%--    <!-- JavaScript Bundle with Popper -->--%>
<%--    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"--%>
<%--            integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"--%>
<%--            crossorigin="anonymous"></script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container-fluid>">--%>
<%--    <div class="row vh-100 align-items-center">--%>
<%--        <%--%>
<%--            UserDTO logIn = (UserDTO) session.getAttribute("logIn");--%>
<%--            if (logIn == null) {--%>
<%--                response.sendRedirect("/index.jsp");--%>
<%--            }--%>

<%--            ConnectionMaker connectionMaker = new MySqlConnectionMaker();--%>
<%--            BoardController boardController = new BoardController(connectionMaker);--%>

<%--            ArrayList<BoardDTO> list = boardController.selectAll();--%>

<%--            if (list.isEmpty()) {--%>
<%--        %>--%>
<%--        <div class="row">--%>
<%--            <div class="col-6">--%>
<%--                <span>아직 등록된 글이 존재하지 않습니다.</span>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--        <%--%>
<%--        } else {--%>
<%--            UserController userController = new UserController(connectionMaker);--%>
<%--            SimpleDateFormat sdf = new SimpleDateFormat("yy년 MM월 dd일 H:m:s");--%>
<%--        %>--%>
<%--        <div class="row justify-content-center">--%>
<%--            <table class="table table-primary table-hover">--%>
<%--                <thead>--%>
<%--                <tr>--%>
<%--                    <th>번호</th>--%>
<%--                    <th>제목</th>--%>
<%--                    <th>작성자</th>--%>
<%--                    <th>작성일</th>--%>
<%--                    <th>수정일</th>--%>
<%--                </tr>--%>
<%--                </thead>--%>
<%--                <tbody>--%>

<%--                <%--%>
<%--                    for (BoardDTO b : list) {--%>
<%--                        String nickname = userController.selectOne(b.getWriterId()).getNickname();--%>
<%--                %>--%>
<%--                <tr class="table-danger">--%>
<%--                    <td>--%>
<%--                        <%=b.getId()%>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <%=b.getTitle()%>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <%=nickname%>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <%=sdf.format(b.getEntryDate())%>--%>
<%--                    </td>--%>
<%--                    <td>--%>
<%--                        <%=sdf.format(b.getModifyDate())%>--%>
<%--                    </td>--%>
<%--                </tr>--%>
<%--                <%--%>
<%--                    }--%>
<%--                %>--%>
<%--                </tbody>--%>
<%--            </table>--%>
<%--        </div>--%>
<%--        <%--%>
<%--            }--%>
<%--        %>--%>
<%--    </div>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
