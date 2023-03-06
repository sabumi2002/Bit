package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;


@MultipartConfig(
        location="C:\\Users\\Sabeom\\IdeaProjects\\taskMovieJsp\\web\\images\\movie",
        fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*10,
        maxRequestSize=1024*1024*10*10
)
@WebServlet("/utils/fileUpload")
public class FileUpload extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Get Request!!");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Post Request!!");

        // 실제 서블릿이 동작하는 서버 경로 (Not 개발 서버)
        String realPath = req.getServletContext().getRealPath("/upload");

        // form 태그 중 name="fileName" 인 요청 파트
        Part filePart = req.getPart("fileName");

        // 요청된 파트의 전송된 파일 이름
        String fileName = filePart.getSubmittedFileName();

        // 입력 스트림
        InputStream fis = filePart.getInputStream();

        // 실제 업로드 될 경로 + 파일명
        String filePath = realPath + File.separator + fileName;

        // 파일 출력 스트림 (저장)
        FileOutputStream fos = new FileOutputStream(filePath);

        // 1024byte 씩 버퍼에 담아 읽어오는 과정
        // write(buffer, offset, length); 를 통해 읽어온 만큼만 쓰는 방법
        byte[] buf = new byte[1024];
        int size = 0;
        while((size=fis.read(buf)) != -1)
            fos.write(buf, 0, size);
        fos.close();
        fis.close();

        PrintWriter out = resp.getWriter();
        out.write("file uploaded to " + realPath +" successfully!");
    }
}