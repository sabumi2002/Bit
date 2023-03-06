package servlet.cinema;

import com.google.gson.JsonObject;
import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import controller.CinemaController;
import model.CinemaDTO;
import model.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CinemaDeleteServlet", value = "/cinema/CinemaDeleteServlet")
public class CinemaDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        JsonObject object = new JsonObject();


        try {
            UserDTO logIn = (UserDTO) session.getAttribute("logIn");
            int cinemaId = Integer.parseInt(request.getParameter("id"));

            ConnectionMaker connectionMaker = new MySqlConnectionMaker();
            CinemaController cinemaController = new CinemaController(connectionMaker);

            CinemaDTO cinemaDTO = cinemaController.selectOne(cinemaId);

            if (cinemaDTO==null || logIn.getRank()!=1){
                throw new NullPointerException();
            }

            cinemaController.delete(cinemaId);

            object.addProperty("status", "success");

        } catch (Exception e){
            object.addProperty("status", "fail");
        }

        writer.print(object);



    }
}


























