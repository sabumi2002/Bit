package servlet.cinema;

import com.google.gson.JsonArray;
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
import java.util.ArrayList;

@WebServlet(name = "CinemaDetailServlet", value = "/cinema/CinemaDetailServlet")
public class CinemaDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JsonObject object = new JsonObject();
        HttpSession session = request.getSession();


        try {

            ConnectionMaker connectionMaker = new MySqlConnectionMaker();
            CinemaController cinemaController = new CinemaController(connectionMaker);

            ArrayList<CinemaDTO> cinemaList = cinemaController.selectAll();

            JsonArray list = new JsonArray();
            for (CinemaDTO c : cinemaList) {
                JsonObject cinemaData = new JsonObject();

                cinemaData.addProperty("id", c.getId());
                cinemaData.addProperty("name", c.getName());
                cinemaData.addProperty("location", c.getLocation());
                cinemaData.addProperty("phoneNumber", c.getPhoneNumber());

                list.add(cinemaData);
            }
            object.addProperty("cinemaList", list.toString());


            UserDTO logIn = (UserDTO) session.getAttribute("logIn");
            object.addProperty("logInRank", logIn.getRank());
            object.addProperty("status", "success");


        } catch (Exception e) {
            object.addProperty("logInRank", -1);
            object.addProperty("status", "fail");
        }


        writer.print(object);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
