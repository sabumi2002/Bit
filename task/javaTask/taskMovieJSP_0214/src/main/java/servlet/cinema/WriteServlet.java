package servlet.cinema;


import com.google.gson.JsonObject;
import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import controller.CinemaController;
import controller.MovieController;
import model.CinemaDTO;
import model.MovieDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 50
)
@WebServlet(name = "WriteServlet", value = "/cinema/WriteServlet")
public class WriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JsonObject object = new JsonObject();
        HttpSession session = request.getSession();

        try {


            String titleName = request.getParameter("title");
            String location = request.getParameter("location");
            String phoneNumber = request.getParameter("phoneNumber");

            if(titleName.equals("")) {
                throw new NullPointerException();
            }
            if(location.equals("")) {
                throw new NullPointerException();
            }
            if(phoneNumber.equals("")) {
                throw new NullPointerException();
            }




            ConnectionMaker connectionMaker = new MySqlConnectionMaker();
            CinemaController cinemaController = new CinemaController(connectionMaker);
            CinemaDTO c = new CinemaDTO();


            if(request.getParameter("file").equals("undefined")){


                c.setName(titleName);
                c.setLocation(location);
                c.setPhoneNumber(phoneNumber);
            }else {
                // ?????? ??????
                Part part = request.getPart("file");


                String fileName = getFilename(part);

                Path dir = Paths.get(request.getSession().getServletContext().getRealPath("/uploads/cinema"));
//            Files.deleteIfExists(dir);
//            Files.createDirectory(dir);

                File f = new File(dir.toRealPath() + "\\" + fileName);
                f.deleteOnExit();
                f.createNewFile();
                part.write(f.getPath());


                c.setName(titleName);
                c.setLocation(location);
                c.setPhoneNumber(phoneNumber);
                c.setImg("/uploads/cinema/" + fileName);

            }

            cinemaController.insert(c);


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
