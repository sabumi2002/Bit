package servlet.movie;


import com.google.gson.JsonObject;
import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import controller.MovieController;
import model.MovieDTO;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;

@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 50
)
@WebServlet(name = "UpdateServlet", value = "/movie/UpdateServlet")
public class UpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JsonObject object = new JsonObject();
        HttpSession session = request.getSession();


//        String location = "upload";
//        ServletContext context = request.getSession().getServletContext();  // 프로젝트 경로
//        String path = context.getRealPath(location);
//
//        int maxSize = 1024 * 1024 * 5; // 키로바이트 * 메가바이트 * 기가바이트
//
//
//        MultipartRequest multi = new MultipartRequest(request,
//                path,
//                maxSize,
//                "utf-8",
//                new DefaultFileRenamePolicy());
//
//        String title = multi.getParameter("title");
//        String content = multi.getParameter("content");
//        String rank = multi.getParameter("rank");
//        String release = multi.getParameter("release");
//        String movieLength = multi.getParameter("length");
//
//        Enumeration<?> files = multi.getFileNames(); // <input type="file">인 모든 파라메타를 반환
//
//        String element = "";
//        String filesystemName = "";
//        String originalFileName = "";
//        String contentType = "";
//        long length = 0;
//
//        if (files.hasMoreElements()) {
//
//            element = (String) files.nextElement();
//
//            filesystemName = multi.getFilesystemName(element);  // 서버에 올라간 파일명
//            originalFileName = multi.getOriginalFileName(element);
//            contentType = multi.getContentType(element);
//            length = multi.getFile(element).length();
//
//        }
//
//        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
//        MovieController movieController = new MovieController(connectionMaker);
//        MovieDTO m = new MovieDTO();
//        m.setTitle(title);
//        m.setContent(content);
//        m.setRank(rank);
//        m.setRelease(release);
//        m.setImg("/upload/"+filesystemName);
//        m.setLength(movieLength);
//
//        movieController.insert(m);
//
//        String currentPage = (String) session.getAttribute("currentPage");
//
//        response.sendRedirect(currentPage);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JsonObject object = new JsonObject();
        HttpSession session = request.getSession();


        for (Part p : request.getParts()) {
        }
        try {


            String titleName = request.getParameter("title");
            String fileDescription = request.getParameter("content");
            String fileRank = request.getParameter("rank");
            String fileRelease = request.getParameter("release");
            String fileLength = request.getParameter("length");
            int movieId = Integer.parseInt(request.getParameter("movieId"));



            // 파일 이름
            Part part = request.getPart("file");


            String fileName = getFilename(part);

            Path dir = Paths.get(request.getSession().getServletContext().getRealPath("upload"));
//            Files.deleteIfExists(dir);
//            Files.createDirectory(dir);

            File f = new File(dir.toRealPath() + "\\" + fileName);
            System.out.println(fileName);
            System.out.println(f.getPath());
            f.deleteOnExit();
            f.createNewFile();
            part.write(f.getPath());

            ConnectionMaker connectionMaker = new MySqlConnectionMaker();
            MovieController movieController = new MovieController(connectionMaker);
            MovieDTO m = movieController.selectOne(movieId);



            m.setTitle(titleName);
            m.setContent(fileDescription);
            m.setRank(fileRank);
            m.setRelease(fileRelease);
            m.setLength(fileLength);
            m.setImg("/upload/"+fileName);

            movieController.update(m);









//            File f = new File("/uploads/" + fileName);


//            if (!f.exists()) {
//                f.createNewFile();
//            }
//            part.write("/uploads/" + fileName);



            object.addProperty("status", "success");
        } catch (Exception e) {
            object.addProperty("status", "fail");
            e.printStackTrace();
        }
        writer.print(object);
    }

    private String getFilename(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] split = contentDisp.split(";");
        for (int i = 0; i < split.length; i++) {
            String temp = split[i];
            if (temp.trim().startsWith("filename")) {
                return temp.substring(temp.indexOf("=") + 2, temp.length() - 1);
            }
        }
        return "";
    }
}
