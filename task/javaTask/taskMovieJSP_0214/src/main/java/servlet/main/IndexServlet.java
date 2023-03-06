package servlet.main;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import connector.ConnectionMaker;
import connector.MySqlConnectionMaker;
import controller.MovieController;
import controller.UserController;
import model.MovieDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "IndexServlet", value = "/main/IndexServlet")
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        JsonObject result = new JsonObject();
        HttpSession session = request.getSession();

        session.setAttribute("currentPage", "/index.jsp");

        ConnectionMaker connectionMaker = new MySqlConnectionMaker();
        UserController userController = new UserController(connectionMaker);
        MovieController movieController = new MovieController(connectionMaker);

        ArrayList<MovieDTO> movieList = movieController.selectAll(1);

        JsonArray movieArray = new JsonArray();

        for(MovieDTO m : movieList) {
            JsonObject object = new JsonObject();
            object.addProperty("id", m.getId());
            object.addProperty("title", m.getTitle());
            object.addProperty("content", m.getContent());
            object.addProperty("rank", m.getRank());
            object.addProperty("release", m.getRelease());
            object.addProperty("length", m.getLength());
            object.addProperty("img", m.getImg());

            movieArray.add(object);
        }
        result.addProperty("result", "success");
        result.addProperty("movieList", movieArray.toString());

        writer.print(result);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
