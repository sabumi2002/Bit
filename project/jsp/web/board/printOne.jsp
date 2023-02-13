<%@ page import="model.UserDTO" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %>
<%@ page import="controller.BoardController" %>
<%@ page import="model.BoardDTO" %>
<%@ page import="controller.UserController" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="controller.ReplyController" %>
<%@ page import="model.ReplyDTO" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-02-10
  Time: 오후 1:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <%
        UserDTO logIn = (UserDTO) session.getAttribute("logIn");
        if (logIn == null) {
            response.sendRedirect("/index.jsp");
        }

        int id = Integer.parseInt(request.getParameter("id"));

        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        BoardController boardController = new BoardController(connectionMaker);
        UserController userController = new UserController(connectionMaker);
        ReplyController replyController = new ReplyController(connectionMaker);


        BoardDTO b = boardController.selectOne(id);
        ArrayList<ReplyDTO> replyList = replyController.selectAll(id);

        pageContext.setAttribute("replyList", replyList);   //jstl
        pageContext.setAttribute("userController", userController);   //jstl

        SimpleDateFormat sdf = new SimpleDateFormat("yy년 MM월 dd일 H:m:s");
    %>
    <script>
        function confirmDelete() {
            let result = confirm("정말로 삭제하시겠습니까?")
            if (result) {
                location.href = "/board/delete.jsp?id=" +
                <%=b.getId()%>
            }
        }
    </script>

    <title><%=b.getTitle()%>
    </title>
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
    <c:set var="b" value="<%=b%>"/>
    <c:set var="logIn" value="<%=logIn%>"/>
    <div class="row align-items-center vh-100">
        <div class="row">
            <div class="col-10 mb-3">
                <table class="table table-striped table-dark">
                    <tr>
                        <th class="col-2">글번호</th>
                        <td class="col-10">${b.id}
                        </td>
                    </tr>
                    <tr>
                        <th>제목</th>
                        <td>${b.title}
                        </td>
                    </tr>
                    <tr>
                        <th>작성자</th>
                        <td><%=userController.selectOne(b.getWriterId()).getNickname()%>
                        </td>
                    </tr>
                    <tr>
                        <th>작성일</th>
                        <td><fmt:formatDate value="${b.entryDate}" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/>
                        </td>
                    </tr>
                    <tr>
                        <th>수정일</th>
                        <td><fmt:formatDate value="${b.modifyDate}" pattern="yyyy년 MM월 dd일 HH시 mm분 ss초"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" class="text-center">
                            ${b.content}
                        </td>
                    </tr>
                    <c:if test="${b.getWriterId() == logIn.getId()}">
                        <tr>
                            <td colspan="2" class="text-center">
                                <div class="btn btn-outline-info" onclick="location.href='printList.jsp'">목록보기</div>
                                <div class="btn btn-outline-success" onclick="location.href='update.jsp?id=${b.id}'">
                                    수정하기
                                </div>
                                <div class="btn btn-outline-danger" onclick="confirmDelete()">삭제하기</div>
                            </td>
                        </tr>
                    </c:if>
                </table>
            </div>
        </div>

        <form action="/reply/write_action.jsp?boardId=${b.id}" method="post">
            <table class="table table-striped">
                <c:choose>
                    <c:when test="${empty replyList}">
                        <tr>
                            <td>
                                아직 등록된 댓글이 존재하지 않습니다.
                            </td>
                        </tr>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${replyList}" var="replyDTO">
                            <tr>
                                <td>
                                    ${userController.selectOne(replyDTO.writerId).nickname}: ${replyDTO.id} ${replyDTO.content}
                                        <fmt:formatDate value="${replyDTO.entryDate}" pattern="yy/MM/dd"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>
                <tr>
                    <td>
                        <input type="text" name="content" class="form-control">
                        <button class="btn btn-outline-light">댓글 달기</button>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>
</body>
</html>






















