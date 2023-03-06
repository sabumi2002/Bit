<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.Enumeration" %>
<%@ page import="model.MovieDTO" %>
<%@ page import="controller.MovieController" %>
<%@ page import="connector.ConnectionMaker" %>
<%@ page import="connector.MySqlConnectionMaker" %><%--
  Created by IntelliJ IDEA.
  User: Sabeom
  Date: 2023-02-22
  Time: 오후 3:24
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    String location = "upload";
    ServletContext context = request.getSession().getServletContext();  // 프로젝트 경로
    String path = context.getRealPath(location);

    int maxSize = 1024 * 1024 * 5; // 키로바이트 * 메가바이트 * 기가바이트

    try {
        MultipartRequest multi = new MultipartRequest(request,
                path,
                maxSize,
                "utf-8",
                new DefaultFileRenamePolicy());

        String title = multi.getParameter("title");
        String content = multi.getParameter("content");
        String rank = multi.getParameter("rank");
        String release = multi.getParameter("release");
        String movieLength = multi.getParameter("length");

        Enumeration<?> files = multi.getFileNames(); // <input type="file">인 모든 파라메타를 반환

        String element = "";
        String filesystemName = "";
        String originalFileName = "";
        String contentType = "";
        long length = 0;

        if (files.hasMoreElements()) {

            element = (String) files.nextElement();

            filesystemName = multi.getFilesystemName(element);  // 서버에 올라간 파일명
            originalFileName = multi.getOriginalFileName(element);
            contentType = multi.getContentType(element);
            length = multi.getFile(element).length();

        }

        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        MovieController movieController = new MovieController(connectionMaker);
        MovieDTO m = new MovieDTO();
        m.setTitle(title);
        m.setContent(content);
        m.setRank(rank);
        m.setRelease(release);
        m.setImg("/upload/" + filesystemName);
        m.setLength(movieLength);

        movieController.insert(m);
    } catch (Exception e) {
        System.out.println("영화추가 에러 발생");
    }

    String currentPage = (String) session.getAttribute("currentPage");

    response.sendRedirect(currentPage);

%>

<%--<p>영화제목 : <%=title%>--%>
<%--</p>--%>
<%--<p>줄거리 : <%=content%>--%>
<%--</p>--%>
<%--<p>등급 : <%=rank%>--%>
<%--</p>--%>
<%--<p>개봉일 : <%=release%>--%>
<%--</p>--%>
<%--<p>상영시간(분) : <%=movieLength%>--%>
<%--</p>--%>
<%--<p>파라메타 이름 : <%=element%>--%>
<%--</p>--%>
<%--<p>서버에 업로드된 파일 이름 : <%=filesystemName%>--%>
<%--</p>--%>
<%--<p>유저가 업로드한 파일 이름 : <%=originalFileName%>--%>
<%--</p>--%>
<%--<p>파일 타입 : <%=contentType%>--%>
<%--</p>--%>
<%--<p>파일 크기 : <%=length%>--%>
<%--</p>--%>
</body>
</html>
