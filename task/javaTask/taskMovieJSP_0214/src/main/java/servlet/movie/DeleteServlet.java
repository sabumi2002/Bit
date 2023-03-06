package servlet.movie;

import com.google.gson.JsonObject;
import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import controller.MovieController;
import model.MovieDTO;
import model.UserDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

@WebServlet(name = "DeleteServlet", value = "/movie/DeleteServlet")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        HttpSession session = request.getSession();
        JsonObject object = new JsonObject();
        String status = "";
        try {
            UserDTO logIn = (UserDTO) session.getAttribute("logIn");
            int movieId = Integer.parseInt(request.getParameter("movieId"));

            ConnectionMaker connectionMaker = new MySqlConnectionMaker();
            MovieController movieController = new MovieController(connectionMaker);
            MovieDTO m = movieController.selectOne(movieId);
            if (m == null || logIn.getRank() != 1) {
                throw new NullPointerException();
            }

            movieController.delete(movieId);
            status = "success";

        } catch (Exception e) {
            status = "fail";
        }

        object.addProperty("status", status);
        writer.print(object);

    }
}
